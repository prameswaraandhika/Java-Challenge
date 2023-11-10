package com.challenge5.app.service;

import com.challenge5.app.model.Merchant;
import com.challenge5.app.repositories.MerchantRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.engine.util.JRSaver;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.ContentDisposition;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;

import javax.sql.DataSource;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@Service
@RequiredArgsConstructor
public class InvoiceService {
    private final MerchantRepository merchantRepository;
    private final DataSource dataSource;

    public ResponseEntity<?> getMerchantReport(){
        List<Merchant> merchantList = merchantRepository.findAll();

        byte[] content = convertToByte(merchantList);
        ByteArrayResource resource = new ByteArrayResource(content);
        return ResponseEntity
                .ok()
                .contentLength(resource.contentLength())
                .header(HttpHeaders.CONTENT_DISPOSITION,
                        ContentDisposition.attachment()
                                .filename("merchantList.pdf")
                                .build()
                                .toString())
                .body(resource);
    }

    private byte[] convertToByte(List<Merchant> merchantList) {
        JasperReport jasperReport;
        try {
            jasperReport = (JasperReport) JRLoader
                    .loadObject(ResourceUtils
                            .getFile("merchantList.jasper"));
        } catch (JRException | FileNotFoundException e) {
            try {
                File file = ResourceUtils.getFile("classpath:jasper/merchantList.jrxml");
                jasperReport = JasperCompileManager.compileReport(file.getAbsolutePath());
                JRSaver.saveObject(jasperReport, "merchantList.jasper");
            } catch (FileNotFoundException | JRException ex) {
                throw new RuntimeException(ex);
            }
        }
        JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(merchantList);
        JasperPrint jasperPrint = null;
        byte[] report;
        try {
            Map<String, Object> parameters = new HashMap<>();
            jasperPrint = JasperFillManager.fillReport(jasperReport,parameters, dataSource);
            report = JasperExportManager.exportReportToPdf(jasperPrint);
        } catch (JRException e) {
            throw new RuntimeException(e);
        }
        return report;
    }
}

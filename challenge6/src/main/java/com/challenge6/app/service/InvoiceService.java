package com.challenge6.app.service;

import com.challenge6.app.model.dtos.MerchantFilterRevenueDto;
import com.challenge6.app.model.dtos.MerchantMonthlyRevenueDto;
import com.challenge6.app.repositories.MerchantRepository;
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

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Slf4j
@Service
@RequiredArgsConstructor
public class InvoiceService {
    private final MerchantRepository merchantRepository;

    public ResponseEntity<?> getMerchantReport(UUID idNerchants, MerchantFilterRevenueDto merchantFilterRevenueDto){
        List<MerchantMonthlyRevenueDto> merchantList = merchantRepository.getMonthlyRevenues(idNerchants,
                merchantFilterRevenueDto.getStart(),
                merchantFilterRevenueDto.getEnd());
        log.info("{} merchants found", merchantList.size());
        byte[] content = convertToByte(merchantList, merchantFilterRevenueDto);
        ByteArrayResource resource = new ByteArrayResource(content);
        return ResponseEntity
                .ok()
                .contentLength(resource.contentLength())
                .header(HttpHeaders.CONTENT_DISPOSITION,
                        ContentDisposition.attachment()
                                .filename("merchant_invoice.pdf")
                                .build()
                                .toString())
                .body(resource);
    }

    private byte[] convertToByte(List<?> merchantList, MerchantFilterRevenueDto merchantFilterRevenueDto) {
        JasperReport jasperReport;
        try {
            jasperReport = (JasperReport) JRLoader
                    .loadObject(ResourceUtils
                            .getFile("Invoice_Merchant_Monthly.jasper"));
        } catch (JRException | FileNotFoundException e) {
            try {
                File file = ResourceUtils.getFile("classpath:jasper/Invoice_Merchant_Monthly.jrxml");
                jasperReport = JasperCompileManager.compileReport(file.getAbsolutePath());
                JRSaver.saveObject(jasperReport, "Invoice_Merchant_Monthly.jasper");
            } catch (FileNotFoundException | JRException ex) {
                throw new RuntimeException(ex);
            }
        }
        JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(merchantList);
        JasperPrint jasperPrint = null;
        byte[] report;
        try {
            Map<String, Object> parameters = new HashMap<>();
            parameters.put("startDate", merchantFilterRevenueDto.getStart());
            parameters.put("endDate", merchantFilterRevenueDto.getEnd());
            jasperPrint = JasperFillManager.fillReport(jasperReport,parameters, dataSource);
            report = JasperExportManager.exportReportToPdf(jasperPrint);
        } catch (JRException e) {
            throw new RuntimeException(e);
        }
        return report;
    }

}

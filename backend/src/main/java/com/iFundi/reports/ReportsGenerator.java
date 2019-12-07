package com.iFundi.reports;

import java.util.HashMap;
import java.util.List;

import com.iFundi.models.Customer;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

public class ReportsGenerator {
    

    public void generatePdfReport(List<Customer> customers) throws JRException {

        String report = "C:\\CompasReports\\testreport.jrxml";

        JasperReport jreport = JasperCompileManager.compileReport(report);

        JRBeanCollectionDataSource ds = new JRBeanCollectionDataSource(customers);

        HashMap<String, Object> params = new HashMap<String, Object>();

        JasperPrint jprint = JasperFillManager.fillReport(jreport, params, ds);

        JasperExportManager.exportReportToPdfFile(jprint,
                "report2.pdf");
    }
}

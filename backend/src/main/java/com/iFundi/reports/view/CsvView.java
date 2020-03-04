package com.iFundi.reports.view;

import java.util.Date;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.supercsv.io.CsvBeanWriter;
import org.supercsv.io.ICsvBeanWriter;
import org.supercsv.prefs.CsvPreference;

import com.iFundi.models.Customer;
public class CsvView extends BaseCsvView{


    @SuppressWarnings("unchecked")
	@Override
    protected void buildCsvDocument(Map<String, Object> model, HttpServletRequest request, HttpServletResponse
            response) throws Exception {

        response.setHeader("Content-Disposition", "attachment; filename=\"Enrolled Customers "+ new Date()+".csv\"");
        //System.out.println(model.get("customers"));
        List<Customer> customers =  (List<Customer>) model.get("customers");
        String[] header = {"FirstName", "LastName","AccountNumber","PBUNo","DateOfBirth","PhoneNumber","EmailAddress"};
        ICsvBeanWriter csvWriter = new CsvBeanWriter(response.getWriter(),
                CsvPreference.STANDARD_PREFERENCE);

        csvWriter.writeHeader(header);

        for (Customer customer : customers) {
            csvWriter.write(customer, header);
        }
        csvWriter.close();

    }

}

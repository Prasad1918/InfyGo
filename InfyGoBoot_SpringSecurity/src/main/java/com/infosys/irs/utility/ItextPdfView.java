package com.infosys.irs.utility;

import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

public class ItextPdfView extends AbstractITextPdfView {



    @Override
    protected void buildPdfDocument(Map<String, Object> model,
                                    Document document, PdfWriter writer, HttpServletRequest request,
                                    HttpServletResponse response) throws DocumentException {



        PdfPTable table = new PdfPTable(3);
        table.setWidths(new int[]{10, 60, 30});

        table.addCell("PNR");
        table.addCell("No of Seats");
        table.addCell("User");
          table.addCell(String.valueOf(model.get("pnr")));
          table.addCell(String.valueOf(model.get("noOfSeats")));
          table.addCell(String.valueOf(model.get("user")));

        document.add(table);
    }

}
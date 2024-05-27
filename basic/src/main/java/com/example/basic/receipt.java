package com.example.basic;

import com.itextpdf.kernel.color.Color;
import com.itextpdf.kernel.geom.PageSize;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.border.Border;
import com.itextpdf.layout.border.DashedBorder;
import com.itextpdf.layout.border.SolidBorder;
import com.itextpdf.layout.element.Cell;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Table;
import com.itextpdf.layout.property.HorizontalAlignment;
import com.itextpdf.layout.property.TextAlignment;
import com.itextpdf.layout.property.VerticalAlignment;

import java.io.FileNotFoundException;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;


public class receipt
{
    public void create() throws FileNotFoundException, SQLException {
        String path = "invoice.pdf";
        PdfWriter pdfWriter = new PdfWriter(path);
        PdfDocument pdfDocument = new PdfDocument(pdfWriter);
        pdfDocument.setDefaultPageSize(PageSize.A4);
        Document document = new Document(pdfDocument);
        float twocol = 285f;
        float twocol150 = twocol+150f;
        float twocolumnWidth[] = {twocol150,twocol};
        float fullwidth[] = {290f,3*190f};
        Paragraph onesp = new Paragraph("\n");

        Table table = new Table(twocolumnWidth);
        table.addCell(getHeaderTextValue("Invoice").setFontSize(30f));
        Table nestedtable = new Table(new float[]{twocol/2,twocol/2});
        int count = 0;
        String sql = "select username,date from customer_receipt";
        database data = new database();
        data.connectDb();
        Statement stmt = data.conn.createStatement();
        ResultSet result =  stmt.executeQuery(sql);
        String username = "";
        while(result.next())
            count++;

        Date date = new Date(System.currentTimeMillis());
        java.sql.Date sqlDate = new java.sql.Date(date.getTime());

        nestedtable.addCell(getHeaderTextCell("Invoice No :"));
        nestedtable.addCell(getHeaderTextValue(String.valueOf(count+1)));
        nestedtable.addCell(getHeaderTextCell("Invoice Date :"));
        nestedtable.addCell(getHeaderTextValue(String.valueOf(date)));

        table.addCell(new Cell().add(nestedtable).setBorder(Border.NO_BORDER));

        Border border;
        border = new SolidBorder(Color.GRAY, 2f);
        Table divider = new Table(fullwidth);
        divider.setBorder(border);

        document.add(table);
        document.add(onesp);
        document.add(divider);
        document.add(onesp);

        Table twoColTable = new Table(twocolumnWidth);
        twoColTable.addCell(getBillingandShippingCell("Customer Information").setFontSize(15));
        document.add(twoColTable.setMarginBottom(12f));

        sql = "Select name,email from miniuser where username = '"+getData.customerusername+"'";
        result = stmt.executeQuery(sql);
        String name = "", email = "";
        while(result.next())
        {
            name = result.getString("name");
            email = result.getString("email");
        }
        float ok[] = {100f,280f};
        Table twoColTable2 = new Table(ok);
        twoColTable2.addCell(getCell10fLeft("Name: ",true).setFontSize(12));
        twoColTable2.addCell(getCell10fLeft(name,false).setFontSize(12).setVerticalAlignment(VerticalAlignment.MIDDLE).setHorizontalAlignment(HorizontalAlignment.LEFT));
        document.add(twoColTable2.setMarginBottom(12f));

        Table twoColTable3 = new Table(ok);
        twoColTable3.addCell(getCell10fLeft("Mobile No: ",true).setFontSize(12));
        twoColTable3.addCell(new Cell().add("01748111111").setFontSize(12).setVerticalAlignment(VerticalAlignment.MIDDLE).setHorizontalAlignment(HorizontalAlignment.LEFT).setBorder(Border.NO_BORDER));
        document.add(twoColTable3.setMarginBottom(12f));

        Table twoColTable4 = new Table(ok);
        twoColTable4.addCell(getCell10fLeft("Email: ",true).setFontSize(12));
        twoColTable4.addCell(getCell10fLeft(email,false).setFontSize(12).setVerticalAlignment(VerticalAlignment.MIDDLE).setBorder(Border.NO_BORDER).setHorizontalAlignment(HorizontalAlignment.LEFT));
        document.add(twoColTable4.setMarginBottom(12f));

        float oneColoumnwidth[]={twocol150};
        Table oneColTable1 = new Table(oneColoumnwidth);
        oneColTable1.addCell(getCell10fLeft("Address:",true).setFontSize(12));
        oneColTable1.addCell(getCell10fLeft("8570 Gulseth terra, 3324 Eastwood\nSpringfi, 01114",false).setFontSize(12));
        document.add(oneColTable1.setMarginBottom(10f));

        Table tableDivider2 = new Table(fullwidth);
        Border dgb = new DashedBorder(Color.GRAY,.8f);
        document.add(tableDivider2.setBorder(dgb));

        document.add(onesp);

        Paragraph productPara = new Paragraph("Products");
        document.add(productPara.setBold().setFontSize(15));

        float threeColumnWidth[]={290f,190f,190f};
        Table threeColtable1 = new Table(threeColumnWidth);
        threeColtable1.setBackgroundColor(Color.BLACK,.7f);

        threeColtable1.addCell(getHeaderTextCell("Description").setFontColor(Color.WHITE).setTextAlignment(TextAlignment.LEFT));
        threeColtable1.addCell(getHeaderTextCell("Quantity").setFontColor(Color.WHITE).setTextAlignment(TextAlignment.CENTER));
        threeColtable1.addCell(getHeaderTextCell("Price").setFontColor(Color.WHITE).setTextAlignment(TextAlignment.RIGHT));
        document.add(threeColtable1);

        sql = "select productmodel,quantity,price from customer where username = '"+getData.customerusername+"' and payment = 'Undone'";
        result = stmt.executeQuery(sql);

        List<Product> productList = new ArrayList<>();
        while(result.next())
        {
            productList.add(new Product(result.getString("productmodel"), result.getString("quantity"),result.getDouble("price")));
        }
        System.out.println();
        Table threeColTable2 = new Table(threeColumnWidth);
        double totalSum = 0f;

        for(Product product:productList)
        {
            double total = Integer.parseInt(product.getQuantity())*product.getPriceperpiece();
            totalSum += total;
            threeColTable2.addCell(new Cell().add(product.getPname()).setBorder(Border.NO_BORDER));
            threeColTable2.addCell(new Cell().add(product.getQuantity()).setBorder(Border.NO_BORDER).setTextAlignment(TextAlignment.CENTER));
            threeColTable2.addCell(new Cell().add(String.valueOf(total)).setTextAlignment(TextAlignment.RIGHT).setBorder(Border.NO_BORDER).setMarginRight(15f));
        }
        document.add(threeColTable2.setMarginBottom(20f));

        float onetwo[] = {190f+125f,190f*2};
        Table threeColTable4 = new Table(onetwo);
        threeColTable4.addCell(new Cell().add("").setBorder(Border.NO_BORDER));
        threeColTable4.addCell(new Cell().add(tableDivider2).setBorder(Border.NO_BORDER));
        document.add(threeColTable4);

        Table threeColTable3 = new Table(threeColumnWidth);
        threeColTable3.addCell(new Cell().add("").setBorder(Border.NO_BORDER).setMarginLeft(10f));
        threeColTable3.addCell(new Cell().add("Total").setTextAlignment(TextAlignment.CENTER).setBorder(Border.NO_BORDER));
        threeColTable3.addCell(new Cell().add(String.valueOf(totalSum)).setTextAlignment(TextAlignment.RIGHT).setBorder(Border.NO_BORDER));
        document.add(threeColTable3);

        document.close();
    }

    static Cell getHeaderTextCell(String textValue)
    {
        return new Cell().add(textValue).setBold().setBorder(Border.NO_BORDER).setTextAlignment(TextAlignment.RIGHT);
    }

    static Cell getHeaderTextValue(String textValue)
    {
        return new Cell().add(textValue).setBold().setBorder(Border.NO_BORDER).setTextAlignment(TextAlignment.LEFT);
    }

    static Cell getBillingandShippingCell(String textValue)
    {
        return new Cell().add(textValue).setFontSize(12f).setBold().setBorder(Border.NO_BORDER).setTextAlignment(TextAlignment.LEFT);
    }

    static Cell getCell10fLeft(String textValue,Boolean isBold)
    {
        Cell myCell = new Cell().add(textValue).setFontSize(10f).setBorder(Border.NO_BORDER).setTextAlignment(TextAlignment.LEFT);
        return isBold?myCell.setBold():myCell;
    }

    class Product
    {
        private String pname;
        private String quantity;
        private Double priceperpiece;

        public Product(String pname, String quantity, Double priceperpeice)
        {
            this.pname= pname;
            this.quantity = quantity;
            this.priceperpiece = priceperpeice;
        }

        public String getPname() {
            return pname;
        }

        public void setPname(String pname) {
            this.pname = pname;
        }

        public String getQuantity() {
            return quantity;
        }

        public void setQuantity(String quantity) {
            this.quantity = quantity;
        }

        public Double getPriceperpiece() {
            return priceperpiece;
        }

        public void setPriceperpiece(Double priceperpiece) {
            this.priceperpiece = priceperpiece;
        }
    }
}

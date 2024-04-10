package com.example.proyecto_01.logic.Services;

import com.example.proyecto_01.logic.Facturas;

import org.jdom2.Document;
import org.jdom2.CDATA;
import org.jdom2.Comment;
import org.jdom2.Element;
import org.jdom2.JDOMException;
import org.jdom2.output.Format;
import org.jdom2.output.XMLOutputter;

import java.io.IOException;
import java.io.OutputStream;

public class XMLService {
    public XMLService() {
    }

    public Document generarDocumentoXML(Facturas factura) {
        Document document = new Document();
        // Crear un elemento raíz para el documento XML
        // Crear un elemento raíz para el documento XML
        Element root = new Element("FacturacionFB");
        Element facturas = new Element("facturas");

        // Agregar elementos y atributos al documento según sea necesario
        Element facturaElement = new Element("factura");
        facturaElement.addContent(new Element("ID").setText(String.valueOf(factura.getIdFactura())));
        //facturaElement.addContent(new Element("ID cliente").setText(String.valueOf(factura.getClientesByIdCliente())));
        //facturaElement.addContent(new Element("ID proveedor").setText(String.valueOf(factura.getProveedoresByIdProveedor())));
        facturaElement.addContent(new Element("Total").setText(String.valueOf(factura.getMonto())));
        facturas.addContent(facturaElement);


        root.addContent(facturas);


        document.setRootElement(root);


        return document;
    }
}

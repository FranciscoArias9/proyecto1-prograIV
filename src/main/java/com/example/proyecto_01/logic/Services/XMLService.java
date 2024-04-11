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
        Element facturas = new Element("Facturas");

        // Agregar elementos y atributos al documento según sea necesario
        Element facturaElement = new Element("Factura");
        facturaElement.addContent(new Element("Id").setText(String.valueOf(factura.getIdFactura())));

        String idCliente = String.valueOf(factura.getClientesByIdCliente().getIdCliente());
        String idProveedor = String.valueOf(factura.getProveedoresByIdProveedor().getIdProveedor());

        facturaElement.addContent(new Element("Id_Cliente").setText(idCliente));
        facturaElement.addContent(new Element("Id_proveedor").setText(idProveedor));
        facturaElement.addContent(new Element("Total").setText(String.valueOf(factura.getMonto())));
        facturas.addContent(facturaElement);


        root.addContent(facturas);


        document.setRootElement(root);


        return document;
    }
}

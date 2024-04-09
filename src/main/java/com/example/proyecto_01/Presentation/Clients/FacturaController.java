package com.example.proyecto_01.Presentation.Clients;


import com.example.proyecto_01.logic.*;
import com.example.proyecto_01.logic.Services.*;
import com.itextpdf.text.Document;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;



import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;


//import javax.xml.bind.JAXBContext;
//import javax.xml.bind.JAXBException;
//import javax.xml.bind.Marshaller;
//import javax.xml.bind.annotation.XmlRootElement;
import java.io.StringWriter;


import java.io.ByteArrayOutputStream;


@Controller
public class FacturaController {

    @Autowired
    private FacturaService facturaService;

    @Autowired
    private ClienteService clienteService;

    @Autowired
    private ProveedorService proveedorService;

    @Autowired
    private ProductoService productoService;

    @Autowired
    private DetalleFacturaService detalleFacturaService;

    List<Productos> listaItems = new ArrayList<>(); //lista de los productos que se van a comprar
    List<Detalle_Factura> listaDetalleFactura = new ArrayList<>();

    @GetMapping("/facturas/new")
    public String mostrarFormularioDeRegistro(Model model, HttpSession session) {

        model.addAttribute("factura", new Facturas());
        model.addAttribute("detalleFactura", new Detalle_Factura());
        model.addAttribute("clientes", clienteService.findAllClientes()); // Añadir la lista de clientes al modelo
        model.addAttribute("proveedores", proveedorService.findAllProveedores()); // Añadir la lista de proveedores al modelo
        model.addAttribute("productos", productoService.findProductosByProveedor((Proveedores) session.getAttribute("proveedor"))); //Agregar la lista de productos al modelo
        model.addAttribute("listaItems", listaItems);
        model.addAttribute("listaDetalles", listaDetalleFactura);
        return "registrar_factura";
    }

    @PostMapping("/facturas/add")
    public String registrarFactura(Facturas factura,HttpSession session, Model model) {

        factura.setProveedoresByIdProveedor((Proveedores) session.getAttribute("proveedor"));
        factura.setMonto(calcularMonto());

        facturaService.saveFactura(factura);

        for(int i = 0; i < listaItems.size(); i++){
            listaDetalleFactura.get(i).setFactura(factura);
            detalleFacturaService.saveDetalleFactura(listaDetalleFactura.get(i));
        }
        listaItems.clear();
        listaDetalleFactura.clear();

        return "redirect:/facturas/new";
    }
    double calcularMonto(){
        double monto = 0;
        for(int i = 0; i < listaItems.size(); i++){
            Double valor = (Double) listaDetalleFactura.get(i).getProducto().getValor();
            int cant = listaDetalleFactura.get(i).getCantidad();
            monto += valor*cant;
        }
        System.out.println("++++++++++++++:" + monto);
        return monto;
    }


    @PostMapping("/facturas/add_Item")
    public String registrarDetalleFactura(@ModelAttribute("productoByIdProducto") Productos producto, @RequestParam("cantidad") int cantidad, Model model) {
        System.out.println("--------------: " + producto.getNombre());
        System.out.println("--------------:" + cantidad);
        Detalle_Factura detalleFactura = new Detalle_Factura();
        detalleFactura.setCantidad(cantidad);
        detalleFactura.setProducto(producto);
        detalleFactura.setPrecioUnitario(10.0);
        listaItems.add(producto);
        listaDetalleFactura.add(detalleFactura);

        //listaDetalleFactura.add();
        //detalleFactura.setFactura(n);


        //facturaService.saveFactura(factura);
        return "redirect:/facturas/new";
    }

    /*@PostMapping("/aumentarCantidad")
    public String aumentarProductoDetalle(Long detalleFactura,HttpSession session, Model model) {

        for (Detalle_Factura indiceObj : listaDetalleFactura) {
            if (Objects.equals(indiceObj.getId_detalle(), detalleFactura)) {
                indiceObj.setCantidad(indiceObj.getCantidad() + 1);
                break;
            }
        }
        return "redirect:/facturas/new";
    }*/

    @PostMapping("/aumentarCantidad")
    public String aumentarCantidad(@RequestParam("index") int index, HttpSession session) {
        // Asumiendo que 'listaDetalleFactura' está guardada en la sesión o es un campo en el controlador
        Detalle_Factura detalle = listaDetalleFactura.get(index);
        detalle.setCantidad(detalle.getCantidad() + 1);
        // Asegúrate de actualizar la sesión o la lista según sea necesario
        return "redirect:/facturas/new";
    }

   /* @PostMapping("/disminuirCantidad")
    public String disminuirProductoDetalle(Long detalleFactura,HttpSession session, Model model) {

        for (Detalle_Factura indiceObj : listaDetalleFactura) {
            if (Objects.equals(indiceObj.getId_detalle(), detalleFactura)) {
                indiceObj.setCantidad(indiceObj.getCantidad() - 1);
                break;
            }
        }
        return "redirect:/facturas/new";
    }*/


    @PostMapping("/disminuirCantidad")
    public String disminuirCantidad(@RequestParam("index") int index, HttpSession session) {
        Detalle_Factura detalle = listaDetalleFactura.get(index);
        detalle.setCantidad(detalle.getCantidad() - 1);
        // Asegúrate de actualizar la sesión o la lista según sea necesario
        return "redirect:/facturas/new";
    }

    @GetMapping("/facturas")
    public String listarFacturas(Model model, HttpSession session) {

        //usuario logeado actualmente
        String usuarioLogeado = (String) session.getAttribute("usuario");
        //Verificar si hay un usuario logeado
        if (usuarioLogeado != null) {
            //lista proveedor actual
            List<Facturas> listaFacturas = facturaService.findFacturasByProveedorActual(usuarioLogeado);
            //Agregar la lista de productos al modelo
            model.addAttribute("facturas", listaFacturas);
        }

        //NUNCA DEBERIA DE SER VACIO YA QUE ENTRO PERO DEBERIAMOS REVISAR EL ASUNTO

        //model.addAttribute("clientes", clienteService.findAllClientes()); // Agrega la lista de clientes al modelo
        //List<Facturas> facturas = facturaService.findAllFacturas();
        //model.addAttribute("facturas", facturas);
        return "lista-facturas"; // nombre del archivo HTML de Thymeleaf
    }

    @GetMapping("/factura/{id}/descargarPDF")
    public void descargarPdf(@PathVariable Long id, HttpServletResponse response) {
        try {
            Facturas factura = facturaService.findFacturaById(id); // Línea completada aquí
            ByteArrayOutputStream baos = generarPdf(factura);
            response.setContentType("application/pdf");
            response.setHeader("Content-Disposition", "attachment; filename=factura-" + id + ".pdf");
            response.getOutputStream().write(baos.toByteArray());
        } catch (IOException e) {
            System.err.println("Error al enviar PDF: " + e.getMessage());
        }
    }

    private ByteArrayOutputStream generarPdf(Facturas factura) {
        // Crear un stream para guardar el PDF
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        // Crear un documento PDF
        Document document = new Document();
        try {
            PdfWriter.getInstance(document, byteArrayOutputStream);
            document.open();
            // Aquí es donde añades contenido al documento. Por ejemplo:
            document.add(new Paragraph("Factura ID: " + factura.getIdFactura()));
            document.add(new Paragraph("Cliente: " + factura.getClientesByIdCliente().getNombre()));
            // Supongamos que tienes un método que calcula el total y lo obtienes así:
            // double total = calcularTotal(factura);
            document.add(new Paragraph("Total: " + factura.getMonto()));
            // Añadir más información de la factura como sea necesario
            document.close();
        } catch (DocumentException e) {
            e.printStackTrace();
        }
        return byteArrayOutputStream;
    }

    private String generarXml(Facturas factura)  {
        //JAXBContext context = JAXBContext.newInstance(Facturas.class);
        //Marshaller marshaller = context.createMarshaller();
        //marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
        StringWriter writer = new StringWriter();

        //marshaller.marshal(factura, writer);
        //return writer.toString();
        return "";
    }

    @GetMapping("/factura/{id}/descargarXML")
    public void descargarXml(@PathVariable Long id, HttpServletResponse response) {
        try {
            // Buscar la factura por ID
            Facturas factura = facturaService.findFacturaById(id);
            if (factura == null) {
                // Manejo en caso de que la factura no se encuentre
                response.sendError(HttpServletResponse.SC_NOT_FOUND, "Factura no encontrada con ID: " + id);
                return;
            }

            // Generar el contenido XML de la factura
            String xmlContent = generarXml(factura);

            // Configurar la respuesta para la descarga del archivo XML
            response.setContentType("application/xml");
            response.setHeader("Content-Disposition", "attachment; filename=factura-" + id + ".xml");

            // Enviar el contenido XML en la respuesta
            response.getOutputStream().write(xmlContent.getBytes());
            response.getOutputStream().flush(); // Asegurarse de que todo el contenido se envíe
        } catch (Exception e) {
            // Log del error (cambiar por un logger adecuado en producción)
            e.printStackTrace();
            try {
                // Enviar una respuesta de error en caso de excepción
                response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Error al generar el XML");
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
        }
    }




}

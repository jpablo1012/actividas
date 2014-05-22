<%@page import="java.net.URLEncoder"%>
<%@page import="java.util.List"%>
<%@page import="org.apache.commons.fileupload.FileItem"%>
<%@page import="org.apache.commons.fileupload.servlet.ServletFileUpload"%>
<%@page import="java.io.File"%>
<%@page import="org.apache.commons.fileupload.disk.DiskFileItemFactory"%>
<%
    String ubicacion = "C://Users//"+System.getProperty("user.name")+"//AppData//archivo";
    File f = new File(ubicacion);
    f.mkdirs();
    DiskFileItemFactory factory = new DiskFileItemFactory();
    factory.setSizeThreshold(1024);
    factory.setRepository(new File(ubicacion));

    ServletFileUpload upload = new ServletFileUpload(factory);
    String nombre = "";
    try {
        List<FileItem> partes = upload.parseRequest(request);
        
        FileItem filei = partes.get(0);
        nombre = filei.getName();
        File file = new File(ubicacion, nombre);
        filei.write(file);
        
        file = null; 
        filei = null;
        partes = null;
        
        try{
            nombre = URLEncoder.encode(nombre, "UTF-8");
        }catch(Exception e){}
        
        response.setContentType("text/html;charset=UTF-8");
        response.getWriter().print("{\"respuesta\": \"0\",\"nombre\": \""+ nombre +"\"}");
        System.out.print("El archivo se ha subido");
    } catch (Exception e) {
     out.write("Error al subir el archivo :|");
        response.getWriter().print("{\"respuesta\": \"1\",\"nombre\": \""+ nombre +"\"}");
        System.out.println("error al subir el archivo");
    }
    
    out.write("Archivo subido correctamente");
    
%>
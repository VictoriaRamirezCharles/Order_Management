package com.micasita.order_management_app.db;

public class ConstantesBaseDatos {

    public static final String DATEBASE_NAME    = "ORDER_MANAGEMENT";
    public static  final int DATABASE_VERSION   = 1;

    public static  final String TBL_ASIGNACIONES                                =   "TBL_ASIGNACIONES";
    public static  final String TBL_ASIGNACIONES_ID                             =   "ID";
    public static  final String TBL_ASIGNACIONES_ORDENID                        =   "ORDENID";
    public static  final String TBL_ASIGNACIONES_EMPLEADOID                     =   "EMPLEADOID";
    public static  final String TBL_ASIGNACIONES_FECHA                          =   "FECHA";
    public static final String TBL_ASIGNACIONES_EMPLEADO_NOMBRE                 =  "EMPLEADO_NOMBRE";


    public static  final String TBL_PRODUCTOS                                   = "TBL_PRODUCTOS";
    public static  final String TBL_PRODUCTOS_ID                                = "ID";
    public static  final String TBL_PRODUCTOS_NOMBRE                            = "NOMBRE";
    public static  final String TBL_PRODUCTOS_DESCRIPCION                       = "DESCRIPCION";
    public static  final String TBL_PRODUCTOS_CANTIDAD                          = "CANTIDAD";
    public static  final String TBL_PRODUCTOS_PRECIO                            = "PRECIO";


    public static  final String TBL_ORDENES                                     = "TBL_ORDENES";
    public static  final String TBL_ORDENES_ID                                  = "ID";
    public static  final String TBL_ORDENES_FECHA                               = "FECHA";
    public static  final String TBL_ORDENES_STATUS                               = "STATUS";

    public static  final String TBL_ORDENES_DETALLE                             = "TBL_ORDENES_DETALLE";
    public static  final String TBL_ORDENES_DETALLE_ID                          = "ID";
    public static  final String TBL_ORDENES_DETALLE_ORDEN_ID                    = "ORDERID";
    public static  final String TBL_ORDENES_DETALLE_PRODUCTO_ID                 = "PRODUCTOID";
    public static  final String TBL_ORDENES_DETALLE_PRECIO                      = "PRECIO";
    public static  final String TBL_ORDENES_DETALLE_CANTIDAD                    = "CANTIDAD";

    public static  final String TBL_EMPLEADOS                                   = "TBL_EMPLEADOS";
    public static  final String TBL_EMPLEADOS_ID                                = "ID";
    public static  final String TBL_EMPLEADOS_CODIGO                            = "CODIGO";
    public static  final String TBL_EMPLEADOS_NOMBRE                            = "NOMBRE";
    public static  final String TBL_EMPLEADOS_DIRECCION                         = "DIRECCION";
    public static  final String TBL_EMPLEADOS_TELEFONO                          = "TELEFONO";
    public static  final String TBL_EMPLEADOS_EMAIL                             = "EMAIL";

}

using Order_Management_WebService.BusinessLayer.Models;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;

namespace Order_Management_WebService.BusinessLayer
{
    public class BusinessModel
    {
        public B_Productos Productos { get { return new B_Productos(); } set { } }
        public B_Ordenes Ordenes { get { return new B_Ordenes(); } set { } }
        public B_Ordenes_Detalle Ordenes_Detalle { get { return new B_Ordenes_Detalle(); } set { } }
        public B_Empleados Empleados { get { return new B_Empleados(); } set { } }
        public B_Asignaciones Asignaciones { get { return new B_Asignaciones(); } set { } }
    }
}
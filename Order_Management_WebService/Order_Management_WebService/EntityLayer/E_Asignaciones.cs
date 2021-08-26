using System;


namespace Order_Management_WebService.EntityLayer
{
    
    public class E_Asignaciones
    {
        public int IdAsignacion { get; set; }
        public int IdOrden { get; set; }
        public int IdEmpleado { get; set; }
        public DateTime Fecha { get; set; }
    }
}
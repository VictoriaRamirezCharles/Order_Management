using System;
using System.Collections.Generic;
using System.ComponentModel.DataAnnotations;
using System.ComponentModel.DataAnnotations.Schema;
using System.Linq;
using System.Web;

namespace Order_Management_WebService.EntityLayer
{
    [Table("TBL_EMPLEADOS")]
    public class E_Empleados
    {
        [Column("IDEMPLEADO"), Key]
        public int IdEmpleado { get; set; }

        [Column("CODIGO")]
        public string Codigo { get; set; }

        [Column("NOMBRE")]
        public string Nombre { get; set; }

        [Column("DIRECCION")]
        public string Direccion { get; set; }

        [Column("TELEFONO")]
        public string Telefono { get; set; }

        [Column("EMAIL")]
        public string Email { get; set; }
    }
}
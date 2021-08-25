using System;
using System.Collections.Generic;
using System.ComponentModel.DataAnnotations;
using System.ComponentModel.DataAnnotations.Schema;
using System.Linq;
using System.Web;

namespace Order_Management_WebService.EntityLayer
{
    [Table("TBL_ASIGNACIONES")]
    public class E_Asignaciones
    {
        [Column("IDASIGNACION"),Key]
        public int IdAsignacion { get; set; }

        [Column("IDORDEN")]
        public int IdOrden { get; set; }

        [Column("IDEMPLEADO")]
        public int IdEmpleado { get; set; }

        [Column("FECHA")]
        public DateTime Fecha { get; set; }
    }
}
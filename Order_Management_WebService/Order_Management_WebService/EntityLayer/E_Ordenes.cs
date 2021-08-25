using System;
using System.Collections.Generic;
using System.ComponentModel.DataAnnotations;
using System.ComponentModel.DataAnnotations.Schema;
using System.Linq;
using System.Web;

namespace Order_Management_WebService.EntityLayer
{
    [Table("TBL_ORDENES")]
    public class E_Ordenes
    {
        [Column("IDORDEN"),Key]
        public int IdOrden { get; set; }
        [Column("FECHAORDEN")]
        public DateTime Fecha { get; set; }
    }
}
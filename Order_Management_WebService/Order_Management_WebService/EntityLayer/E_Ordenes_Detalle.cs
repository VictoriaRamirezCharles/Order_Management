using System;
using System.Collections.Generic;
using System.ComponentModel.DataAnnotations;
using System.ComponentModel.DataAnnotations.Schema;
using System.Linq;
using System.Web;

namespace Order_Management_WebService.EntityLayer
{
    [Table("TBL_ORDENES_DETALLE")]
    public class E_Ordenes_Detalle
    {
        [Column("ID"),Key]
        public int Id { get; set; }

        [Column("IDORDEN")]
        public int IdOrden { get; set; }

        [Column("IDPRODUCTO")]
        public int IdProducto { get; set; }

        [Column("PRECIO")]
        public decimal Precio { get; set; }

        [Column("CANTIDAD")]
        public int Cantidad { get; set; }
    }
}
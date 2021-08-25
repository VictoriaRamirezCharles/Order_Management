using System;
using System.Collections.Generic;
using System.ComponentModel.DataAnnotations;
using System.ComponentModel.DataAnnotations.Schema;
using System.Linq;
using System.Web;

namespace Order_Management_WebService.EntityLayer
{
    [Table("TBL_PRODUCTOS")]
    public class E_Productos
    {
        [Column("IDPRODUCTO"), Key]
        public int IdProducto { get; set; }

        [Column("CODIGO")]
        public string Codigo { get; set; }

        [Column("NOMBRE")]
        public string Nombre { get; set; }

        [Column("DESCRIPCION")]
        public string Descripcion { get; set; }

        [Column("CANTIDAD")]
        public int Cantidad { get; set; }

        [Column("PRECIO")]
        public decimal Precio { get; set; }
    }
}
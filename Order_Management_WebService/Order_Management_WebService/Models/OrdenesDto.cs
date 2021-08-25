using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;

namespace Order_Management_WebService.Models
{
    public class OrdenesDto
    {
        public int Id { get; set; }
        public int Cantidad { get; set; }
        public decimal Precio { get; set; }
        public int IdProducto { get; set; }
    }
}


namespace Order_Management_WebService.EntityLayer
{
    public class E_Ordenes_Detalle
    {
        public int Id { get; set; }
        public int IdOrden { get; set; }
        public int IdProducto { get; set; }
        public decimal Precio { get; set; }
        public int Cantidad { get; set; }
    }
}
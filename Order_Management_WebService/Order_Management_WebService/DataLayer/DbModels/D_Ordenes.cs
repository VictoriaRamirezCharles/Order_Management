using Order_Management_WebService.EntityLayer;
using Order_Management_WebService.Models.Interfaces;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;

namespace Order_Management_WebService.DataLayer.DbModels
{
    public class D_Ordenes : ISelect<E_Ordenes>, IInsert<E_Ordenes>, IDelete<E_Ordenes>, IUpdate<E_Ordenes>
    {
        private Order_Management_Context _dbContext;

        public D_Ordenes()
        {
            _dbContext = new Order_Management_Context();
        }
        public E_Ordenes Add(E_Ordenes data)
        {
            var orden = _dbContext.Ordenes.Add(data);
            _dbContext.SaveChanges();
            return orden;
        }

        public bool DeleteById(int id)
        {
            try
            {
                var orden = GetById(id);
                _dbContext.Ordenes.Remove(orden);
                _dbContext.SaveChanges();
                return true;
            }
            catch (Exception ex)
            {
                return false;
            }


        }

        public List<E_Ordenes> GetAll()
        {
            var ordenes = _dbContext.Ordenes.ToList();

            return ordenes;
        }

        public void Update(E_Ordenes data)
        {
            var orden = GetById(data.IdOrden);
            orden.Fecha = data.Fecha;
            _dbContext.SaveChanges();
        }

        public E_Ordenes GetById(int id)
        {
            var orden = _dbContext.Ordenes.Find(id);

            return orden;
        }

        public IOrderedQueryable<OrdenesDtoModel> GetByModel()
        {
            
            var result = (from o in _dbContext.Ordenes
                          join od in _dbContext.Ordenes_Detalle on o.IdOrden equals od.IdOrden
                          join p in _dbContext.Productos on od.IdProducto equals p.IdProducto
                          select new
                          {
                              Id = o.IdOrden,
                              Codigo = o.Codigo,
                              Fecha = o.Fecha,
                              Precio = od.Precio,
                              Cantidad = od.Cantidad,
                              IdProducto = od.IdProducto,
                              ProductoNombre = p.Nombre,
                              
                          });


            var listOrdenes = result.Select(x => new OrdenesDtoModel
            {
                IdOrden = x.Id,
                Codigo = x.Codigo,
                Fecha = x.Fecha,
                Precio = x.Precio,
                Cantidad = x.Cantidad,
                IdProducto = x.IdProducto,
                ProductoNombre = x.ProductoNombre
            }
            ).OrderBy(x => x.IdOrden);


            return listOrdenes;

        }

        public IOrderedQueryable<OrdenesDtoModel> GetByModelById(int id)
        {

            var result = (from o in _dbContext.Ordenes
                          join od in _dbContext.Ordenes_Detalle on o.IdOrden equals od.IdOrden
                          join p in _dbContext.Productos on od.IdProducto equals p.IdProducto
                          where o.IdOrden == id
                          select new
                          {
                              Id = o.IdOrden,
                              Codigo = o.Codigo,
                              Fecha = o.Fecha,
                              Precio = od.Precio,
                              Cantidad = od.Cantidad,
                              IdProducto = od.IdProducto,
                              ProductoNombre = p.Nombre,

                          });


            var listOrdenes = result.Select(x => new OrdenesDtoModel
            {
                IdOrden = x.Id,
                Codigo = x.Codigo,
                Fecha = x.Fecha,
                Precio = x.Precio,
                Cantidad = x.Cantidad,
                IdProducto = x.IdProducto,
                ProductoNombre = x.ProductoNombre
            }
            ).OrderBy(x => x.IdOrden);


            return listOrdenes;

        }
    }

    public class OrdenesDtoModel
    {
        public int IdOrden { get; set; }
        public string Codigo { get; set; }
        public DateTime Fecha { get; set; }
        public int IdProducto { get; set; }
        public decimal Precio { get; set; }
        public int Cantidad { get; set; }
        public string ProductoNombre { get; set; }
    }
}
using Order_Management_WebService.EntityLayer;
using Order_Management_WebService.Models.Interfaces;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;

namespace Order_Management_WebService.DataLayer.DbModels
{
    public class D_Ordenes_Detalle : ISelect<E_Ordenes_Detalle>, IInsert<E_Ordenes_Detalle>, IDelete<E_Ordenes_Detalle>, IUpdate<E_Ordenes_Detalle>
    {
        private Order_Management_Context _dbContext;

        public D_Ordenes_Detalle()
        {
            _dbContext = new Order_Management_Context();
        }
        public E_Ordenes_Detalle Add(E_Ordenes_Detalle data)
        {
            var orden_detalle = _dbContext.Ordenes_Detalle.Add(data);
            _dbContext.SaveChanges();
            return orden_detalle;
        }

        public bool DeleteById(int id)
        {
            try
            {
                var orden = GetById(id);
                _dbContext.Ordenes_Detalle.RemoveRange(_dbContext.Ordenes_Detalle.Where(x=>x.IdOrden == id));
                _dbContext.SaveChanges();
                return true;
            }
            catch (Exception ex)
            {
                return false;
            }


        }

        public List<E_Ordenes_Detalle> GetAll()
        {
            var ordenes = _dbContext.Ordenes_Detalle.ToList();

            return ordenes;
        }

        public void Update(E_Ordenes_Detalle data)
        {
            var orden = GetById(data.Id);

            orden.IdProducto = data.IdProducto;
            orden.Precio = data.Precio;
            orden.Cantidad = data.Cantidad;

            _dbContext.SaveChanges();
        }

        public E_Ordenes_Detalle GetById(int id)
        {
            var orden = _dbContext.Ordenes_Detalle.Find(id);

            return orden;
        }
    }
}
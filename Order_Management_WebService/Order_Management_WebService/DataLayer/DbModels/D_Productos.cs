using Order_Management_WebService.EntityLayer;
using Order_Management_WebService.Models.Interfaces;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;

namespace Order_Management_WebService.DataLayer.DbModels
{
    public class D_Productos : ISelect<E_Productos>, IInsert<E_Productos>, IDelete<E_Productos>, IUpdate<E_Productos>
    {
        private Order_Management_Context _dbContext;

        public D_Productos()
        {
            _dbContext = new Order_Management_Context();
        }
        public E_Productos Add(E_Productos data)
        {
            var producto = _dbContext.Productos.Add(data);
            _dbContext.SaveChanges();
            return producto;
        }

        public bool DeleteById(int id)
        {
            try
            {
                var producto = GetById(id);
                _dbContext.Productos.Remove(producto);
                _dbContext.SaveChanges();
                return true;
            }
            catch (Exception ex)
            {
                return false;
            }


        }

        public List<E_Productos> GetAll()
        {
            var productos = _dbContext.Productos.ToList();

            return productos;
        }

        public void Update(E_Productos data)
        {
            var producto = GetById(data.IdProducto);
            producto.Nombre = data.Nombre;
            producto.Precio = data.Precio;
            producto.Descripcion = data.Descripcion;
            producto.Cantidad = data.Cantidad;
            _dbContext.SaveChanges();
        }

        public E_Productos GetById(int id)
        {
            var orden = _dbContext.Productos.Find(id);

            return orden;
        }
    }
}
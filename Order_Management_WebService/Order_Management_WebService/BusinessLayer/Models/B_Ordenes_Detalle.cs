using Order_Management_WebService.DataLayer.DbModels;
using Order_Management_WebService.EntityLayer;
using Order_Management_WebService.Models.Interfaces;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;

namespace Order_Management_WebService.BusinessLayer.Models
{
    public class B_Ordenes_Detalle : ISelect<E_Ordenes_Detalle>, IDelete<E_Ordenes_Detalle>, IUpdate<E_Ordenes_Detalle>
    {
        private D_Ordenes_Detalle _context;

        public B_Ordenes_Detalle()
        {
            _context = new D_Ordenes_Detalle();
        }
        public void Add(E_Ordenes_Detalle data)
        {
             _context.Add(data);
   
        }

        public bool DeleteById(int id)
        {
            try
            {
                _context.DeleteById(id);
                return true;

            }
            catch(Exception ex)
            {
                return false;
            }
        }

        public List<E_Ordenes_Detalle> GetAll()
        {
            var ordenes_detalle = _context.GetAll();
            return ordenes_detalle;
        }

        public void Update(E_Ordenes_Detalle data)
        {
            _context.Update(data); ;

        }

        public E_Ordenes_Detalle GetById(int id)
        {
            var orden_detalle = _context.GetById(id);
            return orden_detalle;
        }
    }
}
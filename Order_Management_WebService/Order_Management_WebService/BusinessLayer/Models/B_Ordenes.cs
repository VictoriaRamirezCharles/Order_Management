using Order_Management_WebService.DataLayer.DbModels;
using Order_Management_WebService.EntityLayer;
using Order_Management_WebService.Models.Interfaces;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;

namespace Order_Management_WebService.BusinessLayer.Models
{
    public class B_Ordenes :  IDelete<E_Ordenes>
    {
        private D_Ordenes _context;

        public B_Ordenes()
        {
            _context = new D_Ordenes();
        }
        public void Add(E_Ordenes data)
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
            catch (Exception ex)
            {
                return false;
            }
        }

        public List<OrdenesDtoModel> GetAll()
        {
            var ordenes = _context.GetAll();
            return ordenes;
        }

        public OrdenesDtoModel GetById(int id)
        {
            var orden = _context.GetById(id);
            return orden;
        }
    }
}
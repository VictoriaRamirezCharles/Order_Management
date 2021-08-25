using Order_Management_WebService.DataLayer.DbModels;
using Order_Management_WebService.EntityLayer;
using Order_Management_WebService.Models.Interfaces;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;

namespace Order_Management_WebService.BusinessLayer.Models
{
    public class B_Productos : ISelect<E_Productos>, IInsert<E_Productos>, IDelete<E_Productos>, IUpdate<E_Productos>
    {
        private D_Productos _context;
        public B_Productos()
        {
            _context = new D_Productos();
        }

        public E_Productos Add(E_Productos data)
        {
            var result = _context.Add(data);
            return result;
        }

        public bool DeleteById(int id)
        {
            var result = _context.DeleteById(id);
            return result;
        }

        public List<E_Productos> GetAll()
        {
            var result = _context.GetAll();
            return result;
        }

        public void Update(E_Productos data)
        {
           _context.Update(data);
        }

        public E_Productos GetById(int id)
        {
            var result = _context.GetById(id);
            return result;
        }
    }
}
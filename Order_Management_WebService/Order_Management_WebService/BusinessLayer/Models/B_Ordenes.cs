using Order_Management_WebService.DataLayer.DbModels;
using Order_Management_WebService.EntityLayer;
using Order_Management_WebService.Models.Interfaces;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;

namespace Order_Management_WebService.BusinessLayer.Models
{
    public class B_Ordenes : IInsert<E_Ordenes>, IDelete<E_Ordenes>, IUpdate<E_Ordenes>
    {
        private D_Ordenes _context;

        public B_Ordenes()
        {
            _context = new D_Ordenes();
        }
        public E_Ordenes Add(E_Ordenes data)
        {
            var orden = _context.Add(data);
            return orden;
        }

        public bool DeleteById(int id)
        {
           
            var result = _context.DeleteById(id);
            return result;

        }

        public List<OrdenesDtoModel> GetAll()
        {
            var ordenes = _context.GetByModel().ToList();
            return ordenes;
        }

        public void Update(E_Ordenes data)
        {
           _context.Update(data); ;
          
        }

        public OrdenesDtoModel GetById(int id)
        {
            var orden = _context.GetByModelById(id).FirstOrDefault();
            return orden;
        }
    }
}
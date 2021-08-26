using Order_Management_WebService.DataLayer.DbModels;
using Order_Management_WebService.EntityLayer;
using Order_Management_WebService.Models.Interfaces;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;

namespace Order_Management_WebService.BusinessLayer.Models
{
    public class B_Asignaciones : ISelect<E_Asignaciones>, IDelete<E_Asignaciones>, IUpdate<E_Asignaciones>
    {
        private D_Asignaciones _context;

        public B_Asignaciones()
        {
            _context = new D_Asignaciones();
        }
        public void Add(E_Asignaciones data)
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
            catch( Exception ex)
            {
                return false;
            }
        }

        public List<E_Asignaciones> GetAll()
        {
            var asignaciones = _context.GetAll();
            return asignaciones;
        }

        public void Update(E_Asignaciones data)
        {
            _context.Update(data); ;

        }

        public E_Asignaciones GetById(int id)
        {
            var asignacion = _context.GetById(id);
            return asignacion;
        }
    }
}
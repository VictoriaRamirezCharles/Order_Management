using Order_Management_WebService.DataLayer.DbModels;
using Order_Management_WebService.EntityLayer;
using Order_Management_WebService.Models.Interfaces;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;

namespace Order_Management_WebService.BusinessLayer.Models
{
    public class B_Empleados : ISelect<E_Empleados>, IDelete<E_Empleados>, IUpdate<E_Empleados>
    {
        private D_Empleados _context;

        public B_Empleados()
        {
            _context = new D_Empleados();
        }
        public void Add(E_Empleados data)
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

        public List<E_Empleados> GetAll()
        {
            var empleados = _context.GetAll();
            return empleados;
        }

        public void Update(E_Empleados data)
        {
            _context.Update(data); ;

        }

        public E_Empleados GetById(int id)
        {
            var empleado = _context.GetById(id);
            return empleado;
        }
    }
}
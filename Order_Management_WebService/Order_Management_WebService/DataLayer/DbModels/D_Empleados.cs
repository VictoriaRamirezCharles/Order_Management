using Order_Management_WebService.EntityLayer;
using Order_Management_WebService.Models.Interfaces;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;

namespace Order_Management_WebService.DataLayer.DbModels
{
    public class D_Empleados : ISelect<E_Empleados>, IInsert<E_Empleados>, IDelete<E_Empleados>, IUpdate<E_Empleados>
    {
        private Order_Management_Context _dbContext;

        public D_Empleados()
        {
            _dbContext = new Order_Management_Context();
        }
        public E_Empleados Add(E_Empleados data)
        {
            var empleado = _dbContext.Empleados.Add(data);
            _dbContext.SaveChanges();
            return empleado;
        }

        public bool DeleteById(int id)
        {
            try
            {
                var empleado = GetById(id);
                _dbContext.Empleados.Remove(empleado);
                _dbContext.SaveChanges();
                return true;
            }
            catch (Exception ex)
            {
                return false;
            }
        }

        public List<E_Empleados> GetAll()
        {
            var empleados = _dbContext.Empleados.ToList();

            return empleados;
        }

        public void Update(E_Empleados data)
        {
            var empleado = GetById(data.IdEmpleado);
            empleado.Nombre = data.Nombre;
            empleado.Direccion = data.Direccion;
            empleado.Email = data.Email;
            empleado.Telefono = data.Telefono;
            _dbContext.SaveChanges();
        }

        public E_Empleados GetById(int id)
        {
            var empleado = _dbContext.Empleados.Find(id);

            return empleado;
        }
    }
}
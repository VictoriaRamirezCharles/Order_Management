using Order_Management_WebService.EntityLayer;
using Order_Management_WebService.Models.Interfaces;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;

namespace Order_Management_WebService.DataLayer.DbModels
{
    public class D_Asignaciones : ISelect<E_Asignaciones>, IInsert<E_Asignaciones>, IDelete<E_Asignaciones>, IUpdate<E_Asignaciones>
    {
        private Order_Management_Context _dbContext;

        public D_Asignaciones()
        {
            _dbContext = new Order_Management_Context();
        }
        public E_Asignaciones Add(E_Asignaciones data)
        {
            var asignacion = _dbContext.Asignaciones.Add(data);
            _dbContext.SaveChanges();
            return asignacion;
        }

        public bool DeleteById(int id)
        {
            try
            {
                var asignacion = GetById(id);
                _dbContext.Asignaciones.Remove(asignacion);
                _dbContext.SaveChanges();
                return true;
            }
            catch (Exception ex)
            {
                return false;
            }
        }

        public List<E_Asignaciones> GetAll()
        {
            var asignaciones = _dbContext.Asignaciones.ToList();

            return asignaciones;
        }

        public void Update(E_Asignaciones data)
        {
            var asignacion = GetById(data.IdEmpleado);
            asignacion.IdEmpleado = data.IdEmpleado;
            asignacion.IdEmpleado = data.IdOrden;
            data.Fecha = data.Fecha;
            _dbContext.SaveChanges();
        }

        public E_Asignaciones GetById(int id)
        {
            var asignacion = _dbContext.Asignaciones.Find(id);

            return asignacion;
        }
    }
}
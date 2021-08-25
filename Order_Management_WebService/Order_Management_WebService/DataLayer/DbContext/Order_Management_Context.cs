using Order_Management_WebService.DataLayer.DataAccess;
using Order_Management_WebService.EntityLayer;
using System;
using System.Collections.Generic;
using System.Data.Entity;
using System.Linq;
using System.Web;

namespace Order_Management_WebService.DataLayer
{
    public class Order_Management_Context: DbContext
    {
        public DbSet<E_Asignaciones> Asignaciones { get; set; }
        public DbSet<E_Empleados> Empleados { get; set; }
        public DbSet<E_Ordenes> Ordenes { get; set; }
        public DbSet<E_Ordenes_Detalle> Ordenes_Detalle { get; set; }
        public DbSet<E_Productos> Productos { get; set; }

        public Order_Management_Context()
        {
            Database.Connection.ConnectionString = DatabaseAccess.ConnectionString;
        }
    }
}
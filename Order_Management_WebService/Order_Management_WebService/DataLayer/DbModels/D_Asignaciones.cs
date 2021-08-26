using Order_Management_WebService.DataLayer.DataAccess;
using Order_Management_WebService.EntityLayer;
using Order_Management_WebService.Models.Interfaces;
using System;
using System.Collections.Generic;
using System.Configuration;
using System.Data.SqlClient;
using System.Linq;
using System.Web;
using System.Web.Configuration;

namespace Order_Management_WebService.DataLayer.DbModels
{
    public class D_Asignaciones :D_Base, ISelect<E_Asignaciones>, IUpdate<E_Asignaciones>
    {
        SqlConnection _connection;
        DatabaseAccess _connectionString;

        public D_Asignaciones()
        {
            _connectionString = new DatabaseAccess();
            _connection = new SqlConnection(_connectionString.ConnectionString);
        }

        public void Add(E_Asignaciones item)
        {

            SqlCommand command = new SqlCommand($"insert into TBL_ASIGNACIONES(IDORDEN, IDEMPLEADO, FECHA) values(@IdOrden,@IdEmpleado,@fecha)", _connection);

            _connection.Open();

            command.Parameters.AddWithValue("@IdOrden", item.IdOrden);
            command.Parameters.AddWithValue("@IdEmpleado", item.IdEmpleado);
            command.Parameters.AddWithValue("@fecha", DateTime.Now);
            
            _connectionString.executeDml(command);

            _connection.Close();

        }
        public void DeleteById(int id)
        {
            SqlCommand command = new SqlCommand($"delete TBL_ASIGNACIONES where IDASIGNACION=@id ", _connection);

            _connection.Open();
            command.Parameters.AddWithValue("@id", id);

            _connectionString.executeDml(command);

            _connection.Close();
        }

        public List<E_Asignaciones> GetAll()
        {
            List<E_Asignaciones> Result = new List<E_Asignaciones>();

            string query = $"select * from TBL_ASIGNACIONES";

            SqlCommand Command = new SqlCommand(query, _connection);

            Command.CommandTimeout = Convert.ToInt32(WebConfigurationManager.AppSettings["SQLTimeOut"]);

            _connection.Open();

            var Data = Command.ExecuteReader();

            while (Data.Read())
            {

                Result.Add(new E_Asignaciones
                {
                    IdAsignacion = GetValueManageNull<int>(Data.GetValue(Data.GetOrdinal("IDASIGNACION"))),
                    IdEmpleado = GetValueManageNull<int>(Data.GetValue(Data.GetOrdinal("IDEMPLEADO"))),
                    IdOrden = GetValueManageNull<int>(Data.GetValue(Data.GetOrdinal("IDORDEN"))),
                    Fecha = GetValueManageNull<DateTime>(Data.GetValue(Data.GetOrdinal("FECHA")))
                 });

            }
            _connection.Close();

            return Result;
        }

        public void Update(E_Asignaciones item)
        {
            SqlCommand command = new SqlCommand($"update TBL_ASIGNACIONES set IDORDEN = @IdOrden, IDEMPLEADO = @IdEmpleado, FECHA = @fecha where IDASIGNACION = @IdAsignacion", _connection);

            _connection.Open();

            command.Parameters.AddWithValue("@IdAsignacion", item.IdAsignacion);
            command.Parameters.AddWithValue("@IdOrden", item.IdOrden);
            command.Parameters.AddWithValue("@IdEmpleado", item.IdEmpleado);
            command.Parameters.AddWithValue("@fecha", DateTime.Now);

            _connectionString.executeDml(command);

            _connection.Close();
        }

        public E_Asignaciones GetById(int id)
        {

            var Result = new E_Asignaciones();

            string query = $"select * from TBL_ASIGNACIONES where IDASIGNACION = {id}";

            SqlCommand Command = new SqlCommand(query, _connection);

            Command.CommandTimeout = Convert.ToInt32(WebConfigurationManager.AppSettings["SQLTimeOut"]);

            _connection.Open();

            var Data = Command.ExecuteReader();

            while (Data.Read())
            {

               Result = new E_Asignaciones
                {
                    IdAsignacion = GetValueManageNull<int>(Data.GetValue(Data.GetOrdinal("IDASIGNACION"))),
                    IdEmpleado = GetValueManageNull<int>(Data.GetValue(Data.GetOrdinal("IDEMPLEADO"))),
                    IdOrden = GetValueManageNull<int>(Data.GetValue(Data.GetOrdinal("IDORDEN"))),
                    Fecha = GetValueManageNull<DateTime>(Data.GetValue(Data.GetOrdinal("FECHA")))
                };

            }
            _connection.Close();

            return Result;
        }
    }
}
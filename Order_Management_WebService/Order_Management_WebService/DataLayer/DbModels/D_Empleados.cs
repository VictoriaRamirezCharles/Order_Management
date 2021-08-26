using Order_Management_WebService.DataLayer.DataAccess;
using Order_Management_WebService.EntityLayer;
using Order_Management_WebService.Models.Interfaces;
using System;
using System.Collections.Generic;
using System.Data.SqlClient;
using System.Linq;
using System.Web;
using System.Web.Configuration;

namespace Order_Management_WebService.DataLayer.DbModels
{
    public class D_Empleados: D_Base, ISelect<E_Empleados>, IUpdate<E_Empleados>
    {
        SqlConnection _connection;
        DatabaseAccess _connectionString;

        public D_Empleados()
        {
            _connectionString = new DatabaseAccess();
            _connection = new SqlConnection(_connectionString.ConnectionString);
        }
        public void Add(E_Empleados item)
        {
            SqlCommand command = new SqlCommand($"insert into TBL_EMPLEADOS(NOMBRE, DIRECCION, TELEFONO, EMAIL) values(@nombre,@direccion,@telefono, @email)", _connection);

            _connection.Open();

            command.Parameters.AddWithValue("@nombre", item.Nombre);
            command.Parameters.AddWithValue("@direccion", item.Direccion);
            command.Parameters.AddWithValue("@telefono", item.Telefono);
            command.Parameters.AddWithValue("@email", item.Email);
            _connectionString.executeDml(command);

            _connection.Close();

        }

        public void DeleteById(int id)
        {
            SqlCommand command = new SqlCommand($"delete TBL_EMPLEADOS where IDEMPLEADO = @id ", _connection);

            _connection.Open();
            command.Parameters.AddWithValue("@id", id);

            _connectionString.executeDml(command);

            _connection.Close();
        }

        public List<E_Empleados> GetAll()
        {
            List<E_Empleados> Result = new List<E_Empleados>();

            string query = $"select * from TBL_EMPLEADOS";

            SqlCommand Command = new SqlCommand(query, _connection);

            Command.CommandTimeout = Convert.ToInt32(WebConfigurationManager.AppSettings["SQLTimeOut"]);

            _connection.Open();

            var Data = Command.ExecuteReader();

            while (Data.Read())
            {

                Result.Add(new E_Empleados
                {
                    IdEmpleado = GetValueManageNull<int>(Data.GetValue(Data.GetOrdinal("IDEMPLEADO"))),
                    Codigo = GetValueManageNull<string>(Data.GetValue(Data.GetOrdinal("CODIGO"))),
                    Nombre = GetValueManageNull<string>(Data.GetValue(Data.GetOrdinal("NOMBRE"))),
                    Direccion = GetValueManageNull<string>(Data.GetValue(Data.GetOrdinal("DIRECCION"))),
                    Telefono = GetValueManageNull<string>(Data.GetValue(Data.GetOrdinal("TELEFONO"))),
                    Email = GetValueManageNull<string>(Data.GetValue(Data.GetOrdinal("EMAIL"))),
                });

            }
            _connection.Close();

            return Result;
        }

        public void Update(E_Empleados item)
        {
            SqlCommand command = new SqlCommand($"update TBL_EMPLEADOS set NOMBRE = @nombre, DIRECCION = @direccion, TELEFONO = @telefono, EMAIL = @email where IDEMPLEADO = @id", _connection);

            _connection.Open();

            command.Parameters.AddWithValue("@id", item.IdEmpleado);
            command.Parameters.AddWithValue("@nombre", item.Nombre);
            command.Parameters.AddWithValue("@direccion", item.Direccion);
            command.Parameters.AddWithValue("@telefono", item.Telefono);
            command.Parameters.AddWithValue("@email", item.Email);
            _connectionString.executeDml(command);

            _connection.Close();
        }

        public E_Empleados GetById(int id)
        {
            var Result = new E_Empleados();

            string query = $"select * from TBL_EMPLEADOS where IDEMPLEADO={id}";

            SqlCommand Command = new SqlCommand(query, _connection);

            Command.CommandTimeout = Convert.ToInt32(WebConfigurationManager.AppSettings["SQLTimeOut"]);

            _connection.Open();

            var Data = Command.ExecuteReader();

            while (Data.Read())
            {

                Result = new E_Empleados
                {
                    IdEmpleado = GetValueManageNull<int>(Data.GetValue(Data.GetOrdinal("IDEMPLEADO"))),
                    Codigo = GetValueManageNull<string>(Data.GetValue(Data.GetOrdinal("CODIGO"))),
                    Nombre = GetValueManageNull<string>(Data.GetValue(Data.GetOrdinal("NOMBRE"))),
                    Direccion = GetValueManageNull<string>(Data.GetValue(Data.GetOrdinal("DIRECCION"))),
                    Telefono = GetValueManageNull<string>(Data.GetValue(Data.GetOrdinal("TELEFONO"))),
                    Email = GetValueManageNull<string>(Data.GetValue(Data.GetOrdinal("EMAIL"))),
                };

            }
            _connection.Close();

            return Result;
        }
    }
}
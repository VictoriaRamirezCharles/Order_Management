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
    public class D_Productos : D_Base, ISelect<E_Productos>, IUpdate<E_Productos>
    {
        SqlConnection _connection;
        DatabaseAccess _connectionString;

        public D_Productos()
        {
            _connectionString = new DatabaseAccess();
            _connection = new SqlConnection(_connectionString.ConnectionString);
        }
        public void Add(E_Productos item)
        {
            SqlCommand command = new SqlCommand($"insert into TBL_PRODUCTOS(NOMBRE, DESCRIPCION, PRECIO, CANTIDAD) values(@nombre,@descripcion,@Precio, @Cantidad)", _connection);

            _connection.Open();

            command.Parameters.AddWithValue("@nombre", item.Nombre);
            command.Parameters.AddWithValue("@descripcion", item.Descripcion);
            command.Parameters.AddWithValue("@Precio", item.Precio);
            command.Parameters.AddWithValue("@Cantidad", item.Cantidad);
            _connectionString.executeDml(command);

            _connection.Close();
        }

        public void DeleteById(int id)
        {
            SqlCommand command = new SqlCommand($"delete TBL_PRODUCTOS where IDPRODUCTO = @id ", _connection);

            _connection.Open();
            command.Parameters.AddWithValue("@id", id);

            _connectionString.executeDml(command);

            _connection.Close();


        }

        public List<E_Productos> GetAll()
        {
            List<E_Productos> Result = new List<E_Productos>();

            string query = $"select * from TBL_PRODUCTOS";

            SqlCommand Command = new SqlCommand(query, _connection);

            Command.CommandTimeout = Convert.ToInt32(WebConfigurationManager.AppSettings["SQLTimeOut"]);

            _connection.Open();

            var Data = Command.ExecuteReader();

            while (Data.Read())
            {

                Result.Add(new E_Productos
                {
                    IdProducto = GetValueManageNull<int>(Data.GetValue(Data.GetOrdinal("IDPRODUCTO"))),
                    Codigo = GetValueManageNull<string>(Data.GetValue(Data.GetOrdinal("CODIGO"))),
                    Nombre = GetValueManageNull<string>(Data.GetValue(Data.GetOrdinal("NOMBRE"))),
                    Descripcion = GetValueManageNull<string>(Data.GetValue(Data.GetOrdinal("DESCRIPCION"))),
                    Cantidad = GetValueManageNull<int>(Data.GetValue(Data.GetOrdinal("CANTIDAD"))),
                    Precio = GetValueManageNull<int>(Data.GetValue(Data.GetOrdinal("PRECIO")))

                });

            }
            _connection.Close();

            return Result;
        }
    

        public void Update(E_Productos item)
        {
        SqlCommand command = new SqlCommand($"UPDATE TBL_PRODUCTOS SET NOMBRE = @nombre, DESCRIPCION = @descripcion, PRECIO = @precio, CANTIDAD = @cantidad WHERE IDPRODUCTO = @id", _connection);

        _connection.Open();

        command.Parameters.AddWithValue("@id", item.IdProducto);
        command.Parameters.AddWithValue("@nombre", item.Nombre);
        command.Parameters.AddWithValue("@descripcion", item.Descripcion);
        command.Parameters.AddWithValue("@Precio", item.Precio);
        command.Parameters.AddWithValue("@Cantidad", item.Cantidad);
        _connectionString.executeDml(command);

        _connection.Close();
    }

        public E_Productos GetById(int id)
        {
            var Result = new E_Productos();

            string query = $"select * from TBL_PRODUCTOS where IDPRODUCTO = {id}";

            SqlCommand Command = new SqlCommand(query, _connection);

            Command.CommandTimeout = Convert.ToInt32(WebConfigurationManager.AppSettings["SQLTimeOut"]);

            _connection.Open();

            var Data = Command.ExecuteReader();

            while (Data.Read())
            {

                Result = new E_Productos
                {
                    IdProducto = GetValueManageNull<int>(Data.GetValue(Data.GetOrdinal("IDPRODUCTO"))),
                    Codigo = GetValueManageNull<string>(Data.GetValue(Data.GetOrdinal("CODIGO"))),
                    Nombre = GetValueManageNull<string>(Data.GetValue(Data.GetOrdinal("NOMBRE"))),
                    Descripcion = GetValueManageNull<string>(Data.GetValue(Data.GetOrdinal("DESCRIPCION"))),
                    Cantidad = GetValueManageNull<int>(Data.GetValue(Data.GetOrdinal("CANTIDAD"))),
                    Precio = GetValueManageNull<decimal>(Data.GetValue(Data.GetOrdinal("PRECIO")))

                };

            }
            _connection.Close();

            return Result;
        }
    }
}
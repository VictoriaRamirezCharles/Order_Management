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
    public class D_Ordenes_Detalle : D_Base, ISelect<E_Ordenes_Detalle>, IUpdate<E_Ordenes_Detalle>
    {
        SqlConnection _connection;
        DatabaseAccess _connectionString;

        public D_Ordenes_Detalle()
        {
            _connectionString = new DatabaseAccess();
            _connection = new SqlConnection(_connectionString.ConnectionString);
        }
        public void Add(E_Ordenes_Detalle item)
        {
            SqlCommand command = new SqlCommand($"insert into TBL_ORDENES_DETALLE(IDORDEN, IDPRODUCTO, PRECIO, CANTIDAD) values(@IdOrden,@IdProducto,@Precio, Cantidad)", _connection);

            _connection.Open();

            command.Parameters.AddWithValue("@IdOrden", item.IdOrden);
            command.Parameters.AddWithValue("@IdProducto", item.IdProducto);
            command.Parameters.AddWithValue("@Precio", item.Precio);
            command.Parameters.AddWithValue("@Cantidad", item.Cantidad);
            _connectionString.executeDml(command);

            _connection.Close();
        }

        public void DeleteById(int id)
        {
            SqlCommand command = new SqlCommand($"delete TBL_ORDENES_DETALLE where ID = @id ", _connection);

            _connection.Open();
            command.Parameters.AddWithValue("@id", id);

            _connectionString.executeDml(command);

            _connection.Close();


        }

        public List<E_Ordenes_Detalle> GetAll()
        {
            List<E_Ordenes_Detalle> Result = new List<E_Ordenes_Detalle>();

            string query = $"select * from TBL_ORDENES_DETALLE";

            SqlCommand Command = new SqlCommand(query, _connection);

            Command.CommandTimeout = Convert.ToInt32(WebConfigurationManager.AppSettings["SQLTimeOut"]);

            _connection.Open();

            var Data = Command.ExecuteReader();

            while (Data.Read())
            {

                Result.Add(new E_Ordenes_Detalle
                {
                    Id = GetValueManageNull<int>(Data.GetValue(Data.GetOrdinal("ID"))),
                    IdOrden = GetValueManageNull<int>(Data.GetValue(Data.GetOrdinal("IDORDEN"))),
                    IdProducto = GetValueManageNull<int>(Data.GetValue(Data.GetOrdinal("IDPRODUCTO"))),
                    Cantidad = GetValueManageNull<int>(Data.GetValue(Data.GetOrdinal("CANTIDAD"))),
                    Precio = GetValueManageNull<int>(Data.GetValue(Data.GetOrdinal("PRECIO")))
                    
                });

            }
            _connection.Close();

            return Result;
        }

        public void Update(E_Ordenes_Detalle item)
        {
            SqlCommand command = new SqlCommand($"UPDATE TBL_ORDENES_DETALLE SET IDORDEN = @IdOrden, IDPRODUCTO = @IdProducto, PRECIO = @Precio, CANTIDAD = @Cantidad) values(@IdOrden,@IdProducto,@Precio, Cantidad where ID=@id", _connection);

            _connection.Open();

            command.Parameters.AddWithValue("@id", item.Id);
            command.Parameters.AddWithValue("@IdOrden", item.IdOrden);
            command.Parameters.AddWithValue("@IdProducto", item.IdProducto);
            command.Parameters.AddWithValue("@Precio", item.Precio);
            command.Parameters.AddWithValue("@Cantidad", item.Cantidad);
            _connectionString.executeDml(command);

            _connection.Close();
        }

        public E_Ordenes_Detalle GetById(int id)
        {
            var Result = new E_Ordenes_Detalle();

            string query = $"select * from TBL_ORDENES_DETALLE where ID={id}";

            SqlCommand Command = new SqlCommand(query, _connection);

            Command.CommandTimeout = Convert.ToInt32(WebConfigurationManager.AppSettings["SQLTimeOut"]);

            _connection.Open();

            var Data = Command.ExecuteReader();

            while (Data.Read())
            {

                Result = new E_Ordenes_Detalle
                {
                    Id = GetValueManageNull<int>(Data.GetValue(Data.GetOrdinal("ID"))),
                    IdOrden = GetValueManageNull<int>(Data.GetValue(Data.GetOrdinal("IDORDEN"))),
                    IdProducto = GetValueManageNull<int>(Data.GetValue(Data.GetOrdinal("IDPRODUCTO"))),
                    Cantidad = GetValueManageNull<int>(Data.GetValue(Data.GetOrdinal("CANTIDAD"))),
                    Precio = GetValueManageNull<int>(Data.GetValue(Data.GetOrdinal("PRECIO")))

                };

            }
            _connection.Close();

            return Result;
        }
    }
}
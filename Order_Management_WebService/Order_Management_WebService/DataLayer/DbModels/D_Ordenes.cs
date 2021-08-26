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
    public class D_Ordenes : D_Base
    {
        SqlConnection _connection;
        DatabaseAccess _connectionString;

        public D_Ordenes()
        {
            _connectionString = new DatabaseAccess();
            _connection = new SqlConnection(_connectionString.ConnectionString);
        }
        public void Add(E_Ordenes item)
        {
            SqlCommand command = new SqlCommand($"insert into TBL_ORDENES(FECHAORDEN) values(@fecha)", _connection);

            _connection.Open();

            command.Parameters.AddWithValue("@fecha", item.Fecha);
            _connectionString.executeDml(command);

            _connection.Close();
        }

        public void DeleteById(int id)
        {
            SqlCommand command = new SqlCommand($"delete TBL_ORDENES where IDORDEN = @id ", _connection);

            _connection.Open();
            command.Parameters.AddWithValue("@id", id);

            _connectionString.executeDml(command);

            _connection.Close();

        }

        public List<OrdenesDtoModel> GetAll()
        {
            List<OrdenesDtoModel> Result = new List<OrdenesDtoModel>();

            string query = $@"select o.IDORDEN, o.CODIGO,o.FECHAORDEN, od.IDPRODUCTO as ProductoId, od.PRECIO, od.CANTIDAD, p.Nombre from TBL_ORDENES o inner join TBL_ORDENES_DETALLE od on o.IDORDEN = od.IDORDEN
              inner join TBL_PRODUCTOS p on od.IDPRODUCTO = p.IDPRODUCTO
                            ";

            SqlCommand Command = new SqlCommand(query, _connection);

            Command.CommandTimeout = Convert.ToInt32(WebConfigurationManager.AppSettings["SQLTimeOut"]);

            _connection.Open();

            var Data = Command.ExecuteReader();

            while (Data.Read())
            {

                Result.Add(new OrdenesDtoModel
                {
                    IdOrden = GetValueManageNull<int>(Data.GetValue(Data.GetOrdinal("IDORDEN"))),
                    Codigo = GetValueManageNull<string>(Data.GetValue(Data.GetOrdinal("CODIGO"))),
                    Fecha = GetValueManageNull<DateTime>(Data.GetValue(Data.GetOrdinal("FECHAORDEN"))),
                    Precio = GetValueManageNull<decimal>(Data.GetValue(Data.GetOrdinal("PRECIO"))),
                    Cantidad = GetValueManageNull<int>(Data.GetValue(Data.GetOrdinal("CANTIDAD"))),
                    ProductoNombre = GetValueManageNull<string>(Data.GetValue(Data.GetOrdinal("Nombre"))),
                    IdProducto = GetValueManageNull<int>(Data.GetValue(Data.GetOrdinal("ProductoId"))),
                });

            }
            _connection.Close();

            return Result;
        }

      

        public OrdenesDtoModel GetById(int id)
        {
            var  Result = new OrdenesDtoModel();

            string query = $@"select o.IDORDEN, o.CODIGO,o.FECHAORDEN, od.IDPRODUCTO as ProductoId, od.PRECIO, od.CANTIDAD, p.Nombre from TBL_ORDENES o inner join TBL_ORDENES_DETALLE od on o.IDORDEN = od.IDORDEN
              inner join TBL_PRODUCTOS p on od.IDPRODUCTO = p.IDPRODUCTO where o.IDORDEN = {id}
                            ";

            SqlCommand Command = new SqlCommand(query, _connection);

            Command.CommandTimeout = Convert.ToInt32(WebConfigurationManager.AppSettings["SQLTimeOut"]);

            _connection.Open();

            var Data = Command.ExecuteReader();

            while (Data.Read())
            {

                Result = new OrdenesDtoModel
                {
                    IdOrden = GetValueManageNull<int>(Data.GetValue(Data.GetOrdinal("IDORDEN"))),
                    Codigo = GetValueManageNull<string>(Data.GetValue(Data.GetOrdinal("CODIGO"))),
                    Fecha = GetValueManageNull<DateTime>(Data.GetValue(Data.GetOrdinal("FECHAORDEN"))),
                    Precio = GetValueManageNull<decimal>(Data.GetValue(Data.GetOrdinal("PRECIO"))),
                    Cantidad = GetValueManageNull<int>(Data.GetValue(Data.GetOrdinal("CANTIDAD"))),
                    ProductoNombre = GetValueManageNull<string>(Data.GetValue(Data.GetOrdinal("Nombre"))),
                    IdProducto = GetValueManageNull<int>(Data.GetValue(Data.GetOrdinal("ProductoId"))),
                };

            }
            _connection.Close();

            return Result;
        }

       

      
    }

    public class OrdenesDtoModel
    {
        public int IdOrden { get; set; }
        public string Codigo { get; set; }
        public DateTime Fecha { get; set; }
        public int IdProducto { get; set; }
        public decimal Precio { get; set; }
        public int Cantidad { get; set; }
        public string ProductoNombre { get; set; }
    }
}
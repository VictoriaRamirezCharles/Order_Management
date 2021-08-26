using System;
using System.Collections.Generic;
using System.Configuration;
using System.Data.SqlClient;
using System.Linq;
using System.Web;

namespace Order_Management_WebService.DataLayer.DataAccess
{
    public class DatabaseAccess
    {
        public string ConnectionString { get; private set; }

        public DatabaseAccess()
        {
            LoadConnection();
        }
        public void LoadConnection()
        {

            ConnectionString = ConfigurationManager.ConnectionStrings["defaultConnection"].ConnectionString;
        }
        public bool executeDml(SqlCommand query)
        {
            try
            {
                using (SqlConnection Connection = new SqlConnection(ConnectionString))
                {

                    query.ExecuteNonQuery();

                    return true;
                }
            }
            catch (Exception e)
            {

                throw;
            }


        }

    }
}
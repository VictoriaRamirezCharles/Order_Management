using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;

namespace Order_Management_WebService.DataLayer.DbModels
{
    public class D_Base
    {
        internal static T GetValueManageNull<T>(object value)
        {
            if (value == null || value == DBNull.Value)
            {
                return default(T);
            }
            else
            {
                if (value.GetType() == typeof(int) && typeof(T) == typeof(string))
                {
                    object hola = value.ToString();
                    return (T)hola;
                }
                return (T)value;
            }
        }
    }
}
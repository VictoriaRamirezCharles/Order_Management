using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;

namespace Order_Management_WebService.Models.Interfaces
{
    public interface IDelete<TEntity> where TEntity : class
    {
        bool DeleteById(int id);
    }
}
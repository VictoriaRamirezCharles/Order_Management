using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace Order_Management_WebService.Models.Interfaces
{
    public interface IInsert<TEntity> where TEntity : class
    {
        TEntity Add(TEntity data);

    }
}

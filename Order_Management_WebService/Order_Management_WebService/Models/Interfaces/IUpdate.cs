using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace Order_Management_WebService.Models.Interfaces
{
    public interface IUpdate<TEntity> where TEntity : class
    {
        void Update(TEntity data);
    }
}

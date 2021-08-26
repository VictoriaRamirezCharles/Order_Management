using Order_Management_WebService.EntityLayer;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Net;
using System.Net.Http;
using System.Web.Http;

namespace Order_Management_WebService.Controllers
{
    [RoutePrefix("empleados")]
    public class EmpleadosController : BaseApiController
    {
        [HttpGet]
        [Route("get-all")]
        public IHttpActionResult GetallEmployees()
        {
            return Response(() =>
            {
                var data = _Business.Empleados.GetAll();
                return data;
            });
        }

        [HttpGet]
        [Route("get-by-id/{id}")]
        public IHttpActionResult GetEmployeeById(int id)
        {
            return Response(() =>
            {
                var data = _Business.Empleados.GetById(id);
                return data;
            });
        }

        [HttpPost]
        [Route("add")]
        public IHttpActionResult AddEmployee([FromBody] E_Empleados model)
        {
            return Response((WebResponse res) =>
            {

                try
                {
                    _Business.Empleados.Add(model);
                    res.Code = WebResponse.ResponseCode.success;
                    res.Message = new WebResponse.ResponseMessage
                    {
                        Title = "Success",
                        Body = "The employee has been added successfully."
                    };
                }
                catch (Exception ex)
                {
                    res.Code = WebResponse.ResponseCode.error;
                    res.Message = new WebResponse.ResponseMessage
                    {
                        Title = "Error",
                        Body = $"There is an error."
                    };
                }

                return res;
            });
        }

        [HttpPut]
        [Route("update")]
        public IHttpActionResult UpdateEmployee([FromBody] E_Empleados model)
        {
            return Response((WebResponse res) =>
            {
                try
                {
                    _Business.Empleados.Update(model);
                    res.Code = WebResponse.ResponseCode.success;
                    res.Message = new WebResponse.ResponseMessage
                    {
                        Title = "Success",
                        Body = "The employee has been Updated successfully."
                    };

                }
                catch (Exception ex)
                {
                    res.Code = WebResponse.ResponseCode.error;
                    res.Message = new WebResponse.ResponseMessage
                    {
                        Title = "Error",
                        Body = $"There is an error.{ex.Message}"
                    };
                }

                return res;
            });
        }

        [HttpDelete]
        [Route("delete/{id}")]
        public IHttpActionResult DeleteEmployee(int id)
        {
            return Response((WebResponse res) =>
            {

                var result = _Business.Empleados.DeleteById(id);
                if (result)
                {
                    res.Code = WebResponse.ResponseCode.success;
                    res.Message = new WebResponse.ResponseMessage
                    {
                        Title = "Success",
                        Body = "The employee has been deleted successfully."
                    };
                }
                else
                {
                    res.Code = WebResponse.ResponseCode.error;
                    res.Message = new WebResponse.ResponseMessage
                    {
                        Title = "Error",
                        Body = $"There is an error"
                    };
                }

                return res;
            });
        }
    }
}

using Order_Management_WebService.EntityLayer;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Net;
using System.Net.Http;
using System.Web.Http;

namespace Order_Management_WebService.Controllers
{
    [RoutePrefix("asignaciones")]
    public class AsignacionController : BaseApiController
    {
        [HttpGet]
        [Route("get-all")]
        public IHttpActionResult GetallAsignations()
        {
            return Response(() =>
            {
                var data = _Business.Asignaciones.GetAll();
                return data;
            });
        }

        [HttpGet]
        [Route("get-by-id/{id}")]
        public IHttpActionResult GetAsignationById(int id)
        {
            return Response(() =>
            {
                var data = _Business.Asignaciones.GetById(id);
                return data;
            });
        }

        [HttpPost]
        [Route("add")]
        public IHttpActionResult AddAsignation([FromBody] E_Asignaciones model)
        {
            return Response((WebResponse res) =>
            {
                try
                {
                    _Business.Asignaciones.Add(model);
                    res.Code = WebResponse.ResponseCode.success;
                    res.Message = new WebResponse.ResponseMessage
                    {
                        Title = "Success",
                        Body = "The asignation has been added successfully."
                    };
                }
                catch(Exception ex)
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
        public IHttpActionResult UpdateAsigantion([FromBody] E_Asignaciones model)
        {
            return Response((WebResponse res) =>
            {
                try
                {
                    _Business.Asignaciones.Update(model);
                    res.Code = WebResponse.ResponseCode.success;
                    res.Message = new WebResponse.ResponseMessage
                    {
                        Title = "Success",
                        Body = "The asignation has been Updated successfully."
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
        public IHttpActionResult DeleteAsignacion(int id)
        {
            return Response((WebResponse res) =>
            {

                var result = _Business.Asignaciones.DeleteById(id);
                if (result)
                {
                    res.Code = WebResponse.ResponseCode.success;
                    res.Message = new WebResponse.ResponseMessage
                    {
                        Title = "Success",
                        Body = "The product has been Updated successfully."
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

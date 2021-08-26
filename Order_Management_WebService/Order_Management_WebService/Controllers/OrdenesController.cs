using Order_Management_WebService.EntityLayer;
using Order_Management_WebService.Models;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Net;
using System.Net.Http;
using System.Web.Http;

namespace Order_Management_WebService.Controllers
{
    [RoutePrefix("ordenes")]
    public class OrdenesController : BaseApiController
    {
        [HttpGet]
        [Route("get-all")]
        public IHttpActionResult GetallOrders()
        {
            return Response(() =>
            {
                var data = _Business.Ordenes.GetAll();
                return data;
            });
        }

        [HttpGet]
        [Route("get-by-id/{id}")]
        public IHttpActionResult GetOrderById(int id)
        {
            return Response(() =>
            {
                var data = _Business.Ordenes.GetById(id);
                return data;
            });
        }

        [HttpPost]
        [Route("add")]
        public IHttpActionResult AddOrder([FromBody] OrdenesDto model)
        {
            return Response((WebResponse res) =>
            {
                try
                {
                    var orden = new E_Ordenes();
                    orden.Fecha = DateTime.Now;
                    _Business.Ordenes.Add(orden);

                    var oDetalle = new E_Ordenes_Detalle
                    {
                        IdOrden = model.Id,
                        Precio = model.Precio,
                        Cantidad = model.Cantidad,
                        IdProducto = model.IdProducto

                    };

                    _Business.Ordenes_Detalle.Add(oDetalle);

                    res.Code = WebResponse.ResponseCode.success;
                    res.Message = new WebResponse.ResponseMessage
                    {
                        Title = "Success",
                        Body = "The order has been added successfully."
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
        public IHttpActionResult UpdateOrder([FromBody] OrdenesDto model)
        {
            return Response((WebResponse res) =>
            {
                try
                {
                    var oDetalle = new E_Ordenes_Detalle
                    {
                        Id = model.Id,
                        Precio = model.Precio,
                        Cantidad = model.Cantidad,
                        IdProducto = model.IdProducto

                    };
                    _Business.Ordenes_Detalle.Update(oDetalle);
                    res.Code = WebResponse.ResponseCode.success;
                    res.Message = new WebResponse.ResponseMessage
                    {
                        Title = "Success",
                        Body = "The product has been Updated successfully."
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
        public IHttpActionResult DeleteOrder(int id)
        {
            return Response((WebResponse res) =>
            {

                var result = _Business.Ordenes.DeleteById(id);
                if (result)
                {
                    var resultD = _Business.Ordenes_Detalle.DeleteById(id);
                    if (resultD)
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

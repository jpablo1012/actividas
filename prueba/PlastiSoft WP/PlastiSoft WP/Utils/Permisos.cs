using PlastiSoft_WP.Models.Utils;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace PlastiSoft_WP.Utils
{
    public class Permisos
    {
        public List<FlyoutModel> permisosCrear(int usuario)
        {
            List<FlyoutModel> crear = null;

            if (usuario == 0)
            {
                crear = new List<FlyoutModel>();
                crear.Add(new FlyoutModel() { id = "CrearPedido", texto = "crear pedido" });
                crear.Add(new FlyoutModel() { id = "CrearEmpleado", texto = "crear empleado" });
                crear.Add(new FlyoutModel() { id = "CrearCliente", texto = "crear cliente" });
                crear.Add(new FlyoutModel() { id = "CrearColor", texto = "crear color" });
                crear.Add(new FlyoutModel() { id = "CrearMaterial", texto = "crear material" });
                crear.Add(new FlyoutModel() { id = "CrearBolsa", texto = "crear bolsa" });
            }

            if (usuario == 1)
            {
                crear = new List<FlyoutModel>();
                crear.Add(new FlyoutModel() { id = "CrearPedido", texto = "crear pedido" }); ;
                crear.Add(new FlyoutModel() { id = "CrearBolsa", texto = "crear bolsa" });
            }

            return crear;
        }

        public List<FlyoutModel> permisosBuscar(int usuario)
        {
            List<FlyoutModel> buscar = null;

            if (usuario == 0)
            {
                buscar = new List<FlyoutModel>();
                buscar.Add(new FlyoutModel() { id = "BuscarPedido", texto = "buscar pedido" });
                buscar.Add(new FlyoutModel() { id = "BuscarEmpleado", texto = "buscar empleado" });
                buscar.Add(new FlyoutModel() { id = "BuscarCliente", texto = "buscar cliente" });
                buscar.Add(new FlyoutModel() { id = "BuscarColor", texto = "buscar color" });
                buscar.Add(new FlyoutModel() { id = "BuscarMaterial", texto = "buscar material" });
                buscar.Add(new FlyoutModel() { id = "BuscarBolsa", texto = "buscar bolsa" });
                buscar.Add(new FlyoutModel() { id = "", texto = "" });
            }

            if (usuario == 1)
            {
                buscar = new List<FlyoutModel>();
                buscar.Add(new FlyoutModel() { id = "BuscarPedido", texto = "buscar pedido" }); ;
                buscar.Add(new FlyoutModel() { id = "BuscarBolsa", texto = "buscar bolsa" });
            }

            return buscar;
        }
    }
}

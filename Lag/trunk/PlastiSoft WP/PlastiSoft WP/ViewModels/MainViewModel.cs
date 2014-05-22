using PlastiSoft_WP.ViewModels.Utils;
using System;
using System.Collections.Generic;
using System.Diagnostics;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using Windows.UI.Xaml.Controls;

namespace PlastiSoft_WP.ViewModels
{
    public class MainViewModel
    {
        public int usuario { get; set; }
        public AppBarFlyoutViewModel barraInferior { get; set; }
        public ListaPedidoViewModel ListaPedido { get; set; }

        public MainViewModel()
        {
            usuario = 0;
            barraInferior = new AppBarFlyoutViewModel();
            barraInferior.Usuario = usuario;
            ListaPedido = new ListaPedidoViewModel();
        }

        internal void llenarCrear(object sender, object e)
        {
            var flyout = sender as ListPickerFlyout;
            if (flyout.ItemsSource == null)
            {
                var lista = new List<string>();
                foreach (var c in barraInferior.Crear)
                    lista.Add(c.texto);

                flyout.ItemsSource = lista;
            }


        }

        internal void llenarBuscar(object sender, object e)
        {
            var flyout = sender as ListPickerFlyout;
            if (flyout.ItemsSource == null)
            {
                var lista = new List<string>();
                foreach (var c in barraInferior.Buscar)
                    lista.Add(c.texto);

                flyout.ItemsSource = lista;
            }
        }

    }
}
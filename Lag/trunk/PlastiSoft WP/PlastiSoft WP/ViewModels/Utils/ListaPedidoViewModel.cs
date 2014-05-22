using PlastiSoft_WP.Models;
using PlastiSoft_WP.Services;
using System;
using System.Collections.Generic;
using System.Collections.ObjectModel;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace PlastiSoft_WP.ViewModels.Utils
{
    public class ListaPedidoViewModel
    {
        private ObservableCollection<Pedido> _pedido;

        public ObservableCollection<Pedido> Pedido
        {
            get
            {
                if (_pedido == null)
                    iniciarlizarLista();
                return _pedido;
            }
            set { _pedido = value; }
        }

        public void iniciarlizarLista()
        {
            var spedido = new Spedido();
            _pedido = new ObservableCollection<Pedido>(spedido.getPedidos(20));
        }
    }
}

using PlastiSoft_WP.Models.Utils;
using PlastiSoft_WP.Utils;
using System;
using System.Collections.Generic;
using System.Collections.ObjectModel;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace PlastiSoft_WP.ViewModels.Utils
{
    public class AppBarFlyoutViewModel
    {
        private int _usuario = 0;

        public int MyProperty
        {
            set { _usuario = value; }
        }
        

        private ObservableCollection<FlyoutModel> _crear;

        public ObservableCollection<FlyoutModel> Crear
        {
            get
            {
                if (_crear == null)
                    inicializarCrear();

                return _crear;
            }
            set { _crear = value; }
        }

        private ObservableCollection<FlyoutModel> _buscar;

        public ObservableCollection<FlyoutModel> Buscar
        {
            get
            {
                if (_buscar == null)
                    inicializarBuscar();

                return _buscar;
            }
            set { _buscar = value; }
        }

        public void inicializarCrear()
        {
            var permisos = new Permisos();
            _crear = new ObservableCollection<FlyoutModel>(permisos.permisosCrear(_usuario));
        }

        public void inicializarBuscar()
        {
            var permisos = new Permisos();
            _buscar = new ObservableCollection<FlyoutModel>(permisos.permisosBuscar(_usuario));
        }
    }
}

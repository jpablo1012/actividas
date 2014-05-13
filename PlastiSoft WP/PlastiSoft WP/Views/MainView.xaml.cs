using PlastiSoft_WP.Common;
using PlastiSoft_WP.ViewModels;
using System;
using System.Collections.Generic;
using System.IO;
using System.Linq;
using System.Runtime.InteropServices.WindowsRuntime;
using Windows.Foundation;
using Windows.Foundation.Collections;
using Windows.UI;
using Windows.UI.ViewManagement;
using Windows.UI.Xaml;
using Windows.UI.Xaml.Controls;
using Windows.UI.Xaml.Controls.Primitives;
using Windows.UI.Xaml.Data;
using Windows.UI.Xaml.Input;
using Windows.UI.Xaml.Media;
using Windows.UI.Xaml.Navigation;

// La plantilla Aplicación Hub está documentada en http://go.microsoft.com/fwlink/?LinkId=391641

namespace PlastiSoft_WP.Views
{
    /// <summary>
    /// Página en la que muestra una colección de elementos agrupados.
    /// </summary>
    public sealed partial class MainView : Page
    {
        private NavigationHelper navigationHelper;

        public MainViewModel data
        {
            get
            {
                return DataContext as MainViewModel;
            }
        }
        public MainView()
        {
            this.InitializeComponent();
            navigationHelper = new NavigationHelper(this);

            this.navigationHelper.LoadState += this.NavigationHelper_LoadState;
            this.navigationHelper.SaveState += this.NavigationHelper_SaveState;
            this.Loaded += (o, e) =>
            {
                listaCrear.Opening += data.llenarCrear;
                listaBuscar.Opening += data.llenarBuscar;

                listaCrear.ItemsPicked += selectCrear;
                listaBuscar.ItemsPicked += selectBuscar;
            };

            StatusBar barra = Windows.UI.ViewManagement.StatusBar.GetForCurrentView();
            Color colorBarra = new Color();
            colorBarra.R = 26;
            colorBarra.G = 26;
            colorBarra.B = 26;
            barra.ForegroundColor = colorBarra;
        }

        internal void selectCrear(ListPickerFlyout sender, ItemsPickedEventArgs args)
        {
            sender.Hide();
            if (data.usuario == 0)
            {
                switch (sender.SelectedIndex)
                {
                    case 0:
                        //crear pedido
                        break;
                    case 1:
                        //crear empleado
                        Frame.Navigate(typeof(Empleado.CrearEmpleadoView));
                        break;
                    case 2:
                        //crear cliente
                        Frame.Navigate(typeof(Cliente.CrearClienteView));
                        break;
                    case 3:
                        //crear color
                        break;
                    case 4:
                        //crear material
                        break;
                    case 5:
                        //crear bolsa
                        break;
                }
            }

            if (data.usuario == 1)
            {
                switch (sender.SelectedIndex)
                {
                    case 0:
                        //crear pedido
                        break;
                    case 1:
                        //crear bolsa
                        break;
                }
            }
        }

        internal void selectBuscar(ListPickerFlyout sender, ItemsPickedEventArgs args)
        {
            sender.Hide();
            if (data.usuario == 0)
            {
                switch (sender.SelectedIndex)
                {
                    case 0:
                        //buscar pedido
                        break;
                    case 1:
                        //buscar empleado
                        break;
                    case 2:
                        //buscar cliente
                        break;
                    case 3:
                        //buscar color
                        break;
                    case 4:
                        //buscar material
                        break;
                    case 5:
                        //buscar bolsa
                        break;
                }
            }

            if (data.usuario == 1)
            {
                switch (sender.SelectedIndex)
                {
                    case 0:
                        //buscar pedido
                        break;
                    case 1:
                        //buscar bolsa
                        break;
                }
            }
        }

        /// <summary>
        /// Mantiene el estado asociado con esta página en caso de que se suspenda la aplicación o
        /// se descarte la página de la memoria caché de navegación.  Los valores deben cumplir los requisitos
        /// de serialización de <see cref="SuspensionManager.SessionState"/>.
        /// </summary>
        /// <param name="sender">El origen del evento; suele ser <see cref="NavigationHelper"/></param>
        /// <param name="e">Datos de evento que proporcionan un diccionario vacío para rellenar con
        /// un estado serializable.</param>
        
        private void NavigationHelper_SaveState(object sender, SaveStateEventArgs e)
        {
            // TODO: Guardar aquí el estado único de la página.
        }


        private void NavigationHelper_LoadState(object sender, LoadStateEventArgs e)
        {
            //TODO: Cargar aquí el estado único de la página.
        }

        #region Registro de NavigationHelper

        /// <summary>
        /// Los métodos proporcionados en esta sección se usan simplemente para permitir
        /// que NavigationHelper responda a los métodos de navegación de la página.
        /// <para>
        /// Debe incluirse lógica específica de página en los controladores de eventos para
        /// <see cref="NavigationHelper.LoadState"/>
        /// y <see cref="NavigationHelper.SaveState"/>.
        /// El parámetro de navegación está disponible en el método LoadState
        /// junto con el estado de página mantenido durante una sesión anterior.
        /// </para>
        /// </summary>
        /// <param name="e">Datos de evento que describen cómo se llegó a esta página.</param>
        protected override void OnNavigatedTo(NavigationEventArgs e)
        {
            this.navigationHelper.OnNavigatedTo(e);
        }

        protected override void OnNavigatedFrom(NavigationEventArgs e)
        {
            this.navigationHelper.OnNavigatedFrom(e);
        }

        #endregion
    }
}

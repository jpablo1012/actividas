using System;
using System.Collections.Generic;
using System.Linq;
using System.Net;
using System.Windows;
using System.Windows.Controls;
using System.Windows.Navigation;
using Microsoft.Phone.Controls;
using Microsoft.Phone.Shell;
using Microsoft.Phone.Tasks;
using Plastisoft.Views.Utils;

namespace Plastisoft.Views.Empleado
{
    public partial class crear : PhoneApplicationPage
    {

        public crear()
        {
            InitializeComponent();
            var progress = new ProgressIndicator()
            {
                Text = "Plastisoft",
                IsVisible = true
            };

            SystemTray.SetProgressIndicator(this, progress);
        }


        private void imgEmpleado_Tap(object sender, System.Windows.Input.GestureEventArgs e)
        {
            if (imgEmpleado.Source != null)
            {
                NavigationService.Navigate(new Uri("/Views/Utils/VerImagen.xaml", UriKind.Relative));
                
            }
        }

        protected override void OnNavigatedFrom(NavigationEventArgs e)
        {

            var destino = e.Content as VerImagen;
            if (destino != null){
                try
                {
                    destino.setImagen(imgEmpleado.Source);
                }
                catch (Exception ee) { }
            }
        }

        private void setImagen(object sender, PhotoResult e)
        {
            if (e.TaskResult == TaskResult.OK)
            {
                System.Windows.Media.Imaging.BitmapImage bmp = new System.Windows.Media.Imaging.BitmapImage();
                bmp.SetSource(e.ChosenPhoto);
                imgEmpleado.Source = bmp;
            }
        }

        private void btnElegirFoto_Tap(object sender, System.Windows.Input.GestureEventArgs e)
        {
            PhotoChooserTask photoChooserTask = new PhotoChooserTask();
            photoChooserTask.Completed += new EventHandler<PhotoResult>(setImagen);
            photoChooserTask.Show();
        }

        private void btnTomarFoto_Tap(object sender, System.Windows.Input.GestureEventArgs e)
        {
            CameraCaptureTask cameraCaptureTask = new CameraCaptureTask();
            cameraCaptureTask.Completed += new EventHandler<PhotoResult>(setImagen);
            cameraCaptureTask.Show();

        }

        private void btnQuitarFoto_Tap(object sender, System.Windows.Input.GestureEventArgs e)
        {
            imgEmpleado.Source = null;
        }
    }
}
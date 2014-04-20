using System;
using System.Collections.Generic;
using System.Linq;
using System.Net;
using System.Windows;
using System.Windows.Controls;
using System.Windows.Navigation;
using Microsoft.Phone.Controls;
using Microsoft.Phone.Shell;
using System.Windows.Media;

namespace Plastisoft.Views.Utils
{
    public partial class VerImagen : PhoneApplicationPage
    {

        //private Point? lastOrigin;
        //private double lastUniformScale;

        public VerImagen()
        {
            InitializeComponent();
        }

        public void setImagen(ImageSource bmp)
        {
            imgImagen.Source = bmp;
        }

        //private void Root_ManipulationDelta(object sender, System.Windows.Input.ManipulationDeltaEventArgs e)
        //{
        //    var transform = Root.RenderTransform as CompositeTransform;
        //    if (transform != null)
        //    {
        //        var origin = e.ManipulationContainer.TransformToVisual(this).Transform(e.ManipulationOrigin);

        //        if (!lastOrigin.HasValue)
        //            lastOrigin = origin;

        //        //Calculate uniform scale factor
        //        double uniformScale = Math.Sqrt(Math.Pow(e.CumulativeManipulation.Scale.X, 2) +
        //                                        Math.Pow(e.CumulativeManipulation.Scale.Y, 2));
        //        if (uniformScale == 0)
        //            uniformScale = lastUniformScale;

        //        //Current scale factor
        //        double scale = uniformScale / lastUniformScale;

        //        if (scale > 0 && scale != 1)
        //        {
        //            //Apply scaling
        //            transform.ScaleY = transform.ScaleX *= scale;
        //            //Update the offset caused by this scaling
        //            var ul = Root.TransformToVisual(this).Transform(new Point());
        //            transform.TranslateX = origin.X - (origin.X - ul.X) * scale;
        //            transform.TranslateY = origin.Y - (origin.Y - ul.Y) * scale;
        //        }
        //        //Apply translate caused by drag
        //        transform.TranslateX += (origin.X - lastOrigin.Value.X);
        //        transform.TranslateY += (origin.Y - lastOrigin.Value.Y);

        //        //Cache values for next time
        //        lastOrigin = origin;
        //        lastUniformScale = uniformScale;
        //    }

        //}

        //private void Root_ManipulationStarted(object sender, System.Windows.Input.ManipulationStartedEventArgs e)
        //{
        //    lastUniformScale = Math.Sqrt(2);
        //    lastOrigin = null;
        //}
    }
}
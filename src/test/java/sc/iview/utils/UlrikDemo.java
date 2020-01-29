package sc.iview.utils;

import bdv.util.BdvFunctions;
import bdv.util.BdvOptions;
import ij.IJ;
import ij.ImagePlus;
import net.imglib2.RandomAccessibleInterval;
import net.imglib2.img.Img;
import net.imglib2.img.display.imagej.ImageJFunctions;
import net.imglib2.type.numeric.integer.UnsignedShortType;
import net.imglib2.view.Views;
import org.janelia.saalfeldlab.n5.GzipCompression;
import org.janelia.saalfeldlab.n5.N5FSReader;
import org.janelia.saalfeldlab.n5.N5FSWriter;
import org.janelia.saalfeldlab.n5.imglib2.N5Utils;

import java.io.IOException;

public class UlrikDemo {

    public static void main(String... args) throws IOException {

        String tiffName = "/home/kharrington/Data/Korgaonkar_Aishwarya/Stilate/2/c2.tif";

        String n5Path = "/tmp/my.n5";
        String datasetName = "/2/c2";

        ImagePlus tiff = IJ.openImage(tiffName);
        tiff.show();

        N5FSWriter n5 = new N5FSWriter(n5Path);

        RandomAccessibleInterval<UnsignedShortType> rai = ImageJFunctions.wrap(tiff);

        N5Utils.save(rai, n5, datasetName, new int[]{128, 128, 128}, new GzipCompression());

        RandomAccessibleInterval<UnsignedShortType> volatileRai = N5Utils.openVolatile(new N5FSReader(n5Path), datasetName);

        BdvFunctions.show(volatileRai, "volatile", BdvOptions.options());

    }

}

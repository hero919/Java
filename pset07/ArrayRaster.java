/**
 * Created by zeqingzhang on 11/7/14.
 */
public final class ArrayRaster extends AbstractRaster {
     private int[] buffer;

    ArrayRaster(int width,int height){
       super(width,height);

       buffer= new int[width*height];
    }

    ArrayRaster(int width,int height,int[] buffer){
        super(width,height);

        if (buffer == null) {
            this.buffer = new int[width * height];
        }

        else if(buffer.length>=width*height){
            this.buffer=buffer;

            }


       else {
            throw new IllegalArgumentException("The given ArrayRaster is not valid");
        }
    }



    public int[] getBuffer(){
        return this.buffer;
    }






    public int getRGBA(Point<Integer> point){
        return buffer[point.intY()*width()+point.intX()];
    }





    @Override
    public void setRGBA(Point<Integer> point, int colorValue) {

        buffer[(point.intX())+point.intY()*width()]=colorValue;

    }








}

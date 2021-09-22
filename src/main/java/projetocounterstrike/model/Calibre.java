package projetocounterstrike.model;

/**
 *
 * @author ruan_
 */
public enum Calibre {
    C03, C05, C08;

        public static Calibre getCalibre(String nameEnum){
        
        if(nameEnum.equals(Calibre.C03.toString()))
            
            return Calibre.C03;
        
        else if(nameEnum.equals(Calibre.C05.toString())){
            
            return Calibre.C05;
            
        }else if(nameEnum.equals(Calibre.C08.toString())){
            
            return Calibre.C08;
            
        }else{
            return null;
        }
    }
}

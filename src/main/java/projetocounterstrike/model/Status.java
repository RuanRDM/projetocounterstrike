package projetocounterstrike.model;

/**
 *
 * @author ruan_
 */
public enum Status {
    SIM, NAO;

        public static Status getStatus(String nameEnum){
        
        if(nameEnum.equals(Status.SIM.toString()))
            
            return Status.SIM;
        
        else if(nameEnum.equals(Status.NAO.toString())){
            
            return Status.NAO;
            
        }else{
            return null;
        }
    }
}
